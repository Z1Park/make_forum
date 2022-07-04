package MyProject.MyWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDto {

    private Long order;
    private Long uploaderId;
    private String title;
    private String uploaderName;
    private int recommend;
    private String time;
}
