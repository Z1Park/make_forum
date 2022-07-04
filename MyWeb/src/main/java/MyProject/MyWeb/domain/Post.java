package MyProject.MyWeb.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Post {

    private Long postOrder;
    private Long uploaderId;
    private String postTitle;
    private String uploaderName;
    private String postBody;
    private LocalDateTime postTime;

    private int upCount;
    private int downCount;
    private List<String > recommends;

    private List<Comment> comments;

    public Post(Long postOrder, Long uploaderId, String uploaderName, String postTitle, String postBody, LocalDateTime postTime) {
        this.postOrder = postOrder;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTime = postTime;
    }

    public void updatePost(String uploader, String postTitle, String postBody, LocalDateTime postTime) {

        // TODO uploader 검증 로직 추가

        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTime = postTime;
    }
}
