package MyProject.MyWeb.repository;

import MyProject.MyWeb.domain.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface PostRepository {

    Post save(Long userId, String title, String userName, String body, LocalDateTime time);

    Post findById(Long postId);

    List<Post> findAll();

    void deletePost(Long postId);

    void updatePost(Long postId, String title, String body, LocalDateTime time);
}
