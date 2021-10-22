package sj.simpleboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sj.simpleboard.domain.Member;
import sj.simpleboard.service.MemberServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {

    private final MemberServiceImpl memberServiceImpl;

    public LoginController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @PostMapping("/members/add")
    public String memberAdd(@ModelAttribute @Validated Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "members/memberAdd";
        }
        if (!memberServiceImpl.join(member)) {
            bindingResult.rejectValue("memberId", "dupId", "중복된 아이디입니다");
            log.info("errors ={}", bindingResult);
            return "members/memberAdd";
        }
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, HttpServletRequest request, BindingResult bindingResult, Model model
                        ,@RequestParam(defaultValue = "/home") String redirectURL
    ) {
        boolean chkPwd = memberServiceImpl.chkPwd(member.getMemberId(), member.getMemberPwd());
        if (!chkPwd) {
            model.addAttribute("member", member);
            bindingResult.reject("noLogin", "아이디나 비밀번호가 틀렸습니다");
            return "login/loginForm";
        }
        memberServiceImpl.newSession(request, member);
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/home";
    }
}
