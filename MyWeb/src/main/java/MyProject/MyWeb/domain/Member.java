package MyProject.MyWeb.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Member {

     private Long id;

     @NotBlank
     private String name;

     @NotBlank
     private String userId;

//     @NotBlank
//     private String password;

     private List<Long> posts;

     public Member(Long id, String name, String userId) {
          this.id = id;
          this.name = name;
          this.userId = userId;
          this.posts = new ArrayList<>();
     }

     public void addPost(Long postOrder) {
          this.posts.add(postOrder);
     }
}

