package MyProject.MyWeb.repository;

import MyProject.MyWeb.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class PostRepositoryImpl implements PostRepository{

    // Map 임시 사용 : 추후 DB 대체
    private static Map<Long, Post> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public Post save(Long userId, String title, String userName, String body, LocalDateTime time) {
        Post post = new Post(++sequence, userId, userName, title, body, time);
        store.put(post.getPostOrder(), post);
        return post;
    }

    @Override
    public Post findById(Long postId) {
        return store.get(postId);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deletePost(Long postId) {
        store.remove(postId);
    }

    @Override
    public void updatePost(Long postId, String title, String body, LocalDateTime time) {
        Post findPost = findById(postId);
        // TODO
    }
}
