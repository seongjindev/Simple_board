package sj.simpleboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sj.simpleboard.domain.Member;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/members/add")
    public String memberAdd(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "members/memberAdd";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "login/loginForm";
    }

}
