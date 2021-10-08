package sj.simpleboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sj.simpleboard.domain.Member;
import sj.simpleboard.repository.BoardRepository;
import sj.simpleboard.repository.MysqlBoardRepository;
import sj.simpleboard.service.MemberService;

@Slf4j
@Controller
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/add")
    public String memberAdd(@ModelAttribute @Validated Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "members/memberAdd";
        }
        if (!memberService.join(member)) {
            bindingResult.rejectValue("memberId", "dupId", "중복된 아이디입니다");
            log.info("errors ={}", bindingResult);
            return "members/memberAdd";
        }
        String pass = "회원가입이 완료되었습니다";
        model.addAttribute("pass", pass);
        return "home";
    }
}
