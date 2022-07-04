package MyProject.MyWeb.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class Comment {

    private Long commentOrder;
    private String userId;
    private String commentBody;
    private LocalDateTime commentTime;
    private int upCount;
    private int downCount;

    public Comment(String userId, String commentBody, LocalDateTime commentTime) {
        this.userId = userId;
        this.commentBody = commentBody;
        this.commentTime = commentTime;
    }

    public void updateComment(String userId, String commentBody, LocalDateTime commentTime) {

        // TODO userId 검증 로직 추가

        this.commentBody = commentBody;
        this.commentTime = commentTime;
    }
}
