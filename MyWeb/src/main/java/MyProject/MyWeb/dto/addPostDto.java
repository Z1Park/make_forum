package MyProject.MyWeb.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class addPostDto {

    private String title;
    private String userId;
    private String body;

    public addPostDto() {
    }
}
