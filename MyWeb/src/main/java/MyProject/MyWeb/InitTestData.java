package MyProject.MyWeb;

import MyProject.MyWeb.domain.Member;
import MyProject.MyWeb.repository.MemberRepository;
import MyProject.MyWeb.repository.PostRepository;
import MyProject.MyWeb.service.MemberService;
import MyProject.MyWeb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitTestData {

    private final MemberService memberService;
    private final PostService postService;

    @PostConstruct
    public void init() {
        Member memberA = memberService.signUp("userName1", "userA");
        Member memberB = memberService.signUp("userName2", "userB");
        Member memberC = memberService.signUp("userName3", "userC");

        String body = "Rod Johnson, Juergen Hoeller, Keith Donald, Colin Sampaleanu, Rob Harrop, Thomas Risberg, Alef Arendsen, Darren Davison, Dmitriy Kopylenko, Mark Pollack, Thierry Templier, Erwin Vervaet, Portia Tung, Ben Hale, Adrian Colyer, John Lewis, Costin Leau, Mark Fisher, Sam Brannen, Ramnivas Laddad, Arjen Poutsma, Chris Beams, Tareq Abedrabbo, Andy Clement, Dave Syer, Oliver Gierke, Rossen Stoyanchev, Phillip Webb, Rob Winch, Brian Clozel, Stephane Nicoll, Sebastien Deleuze, Jay Bryant, Mark Paluch\n" +
                "\n" +
                "Copyright © 2002 - 2022 Pivotal, Inc. All Rights Reserved.\n" +
                "\n" +
                "Copies of this document may be made for your own use and for distribution to others, provided that you do not charge any fee for such copies and further provided that each copy contains this Copyright Notice, whether distributed in print or electronically.";

        postService.uploadPost("post1", memberA.getUserId(), body, LocalDateTime.now());
        postService.uploadPost("post2", memberB.getUserId(), "postBody2", LocalDateTime.now());
        postService.uploadPost("post3", memberC.getUserId(), "postBody3", LocalDateTime.now());
    }
}
