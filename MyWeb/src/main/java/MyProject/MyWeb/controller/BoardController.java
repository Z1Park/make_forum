package MyProject.MyWeb.controller;

import MyProject.MyWeb.domain.Member;
import MyProject.MyWeb.dto.BoardDto;
import MyProject.MyWeb.dto.PostDto;
import MyProject.MyWeb.dto.addPostDto;
import MyProject.MyWeb.service.MemberService;
import MyProject.MyWeb.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/board")
    public String board(@RequestParam(value = "member", required = false) Long memberId, Model model) {
        if (memberId == null) {
            List<BoardDto> posts = postService.findAll().stream().limit(20).collect(Collectors.toList());
            model.addAttribute("posts", posts);
        } else {
            Member member = memberService.findById(memberId);
            List<Long> postOrders = member.getPosts();
            List<BoardDto> posts = postService.findAllByOrders(postOrders);
            model.addAttribute("posts", posts);
        }
        return "board/main";
    }

    @GetMapping("/{postOrder}")
    public String post(@PathVariable Long postOrder, Model model) {
        PostDto post = postService.findByPostOrder(postOrder);
        model.addAttribute("post", post);
        return "board/post";
    }

    @GetMapping("/board/addPost")
    public String addPostForm(Model model) {
        model.addAttribute("post", new addPostDto());
        return "board/addPost";
    }

    @PostMapping("/board/addPost")
    public String addPost() {
        return "redirect:/board";
    }
}
