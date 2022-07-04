package MyProject.MyWeb.dto;

import MyProject.MyWeb.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostDto {

    private Long order;
    private Long uploaderId;
    private String title;
    private String uploaderName;
    private String body;
    private String time;

    private int up;
    private int down;

    private List<Comment> comments;
}
