package MyProject.MyWeb.service;

import MyProject.MyWeb.domain.Member;
import MyProject.MyWeb.domain.Post;
import MyProject.MyWeb.repository.MemberRepository;
import MyProject.MyWeb.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Member signUp(String name, String userId) {
        return memberRepository.save(name, userId);
    }

    public List<Post> getUserPosts(String userId) {
        Optional<Member> member = memberRepository.findByUserId(userId);
        List<Post> posts = new ArrayList<>();
        List<Long> postOrders = member.get().getPosts();
        postOrders.stream().forEach(p -> posts.add(postRepository.findById(p)));
        return posts;
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }
}
