package MyProject.MyWeb.service;

import MyProject.MyWeb.domain.Member;
import MyProject.MyWeb.domain.Post;
import MyProject.MyWeb.dto.BoardDto;
import MyProject.MyWeb.dto.PostDto;
import MyProject.MyWeb.repository.MemberRepository;
import MyProject.MyWeb.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void uploadPost(String title, String userId, String body, LocalDateTime time) {
        Optional<Member> member = memberRepository.findByUserId(userId);
        Post saved = postRepository.save(member.get().getId(), title, userId, body, time);
        memberRepository.addPost(member.get().getUserId(), saved.getPostOrder());
    }

    public List<BoardDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<BoardDto> boardDto = new ArrayList<>();
        for (Post post : posts) {
            boardDto.add(new BoardDto(post.getPostOrder(), post.getUploaderId(), post.getPostTitle(),
                    post.getUploaderName(), post.getUpCount(), calculateTime(post.getPostTime())));
        }
        return boardDto;
    }

    public PostDto findByPostOrder(Long postOrder) {
        Post post = postRepository.findById(postOrder);
        PostDto postDto = new PostDto(post.getPostOrder(), post.getUploaderId(), post.getPostTitle(),
                post.getUploaderName(), post.getPostBody(), calculateTime(post.getPostTime()),
                post.getUpCount(), post.getDownCount(), post.getComments());
        return postDto;
    }

    public List<BoardDto> findAllByOrders(List<Long> postOrders) {
        List<BoardDto> boardDto = new ArrayList<>();
        for (Long postOrder : postOrders) {
            Post post = postRepository.findById(postOrder);
            boardDto.add(new BoardDto(post.getPostOrder(), post.getUploaderId(), post.getPostTitle(),
                    post.getUploaderName(), post.getUpCount(), calculateTime(post.getPostTime())));
        }
        return boardDto;
    }

    private String calculateTime(LocalDateTime uploadTime) {
        LocalDateTime nowTime = LocalDateTime.now();
        long diff = SECONDS.between(uploadTime, nowTime);
        if (diff < 60) {
            return String.valueOf(diff) + " 초 전";
        }
        diff = MINUTES.between(uploadTime, nowTime);
        if (diff < 60) {
            return String.valueOf(diff) + " 분 전";
        }
        diff = HOURS.between(uploadTime, nowTime);
        if (diff < 24) {
            return String.valueOf(diff) + " 시간 전";
        }
        diff = DAYS.between(uploadTime, nowTime);
        if (diff < 7) {
            return String.valueOf(diff) + " 일 전";
        }
        diff = WEEKS.between(uploadTime, nowTime);
        if (diff < 31) {
            return String.valueOf(diff) + " 주 전";
        }
        diff = MONTHS.between(uploadTime, nowTime);
        if (diff < 12) {
            return String.valueOf(diff) + " 달 전";
        }
        diff = YEARS.between(uploadTime, nowTime);
        return String.valueOf(diff) + " 년 전";
    }
}
