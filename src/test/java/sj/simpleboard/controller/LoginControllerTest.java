package sj.simpleboard.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.Errors;
import sj.simpleboard.domain.Member;

import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest //repository 와 service 를 메모리에 올리지 않는다, //@SpringBootTest의 MockMvc와 충돌이 발생할 수 있음
@AutoConfigureMockMvc //repository 와 service 를 메모리에 올린다
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LoginController loginController;

    @Test
    void 로그인요청_확인() throws Exception {
        //given //when //then
        mockMvc.perform(post("/login")
                .param("memberId","aaafa").param("memberPwd", "faaa"))
                .andExpect(status().isOk());
    }

    @Test
    void 비밀번호_길이_제한() throws Exception {
        //given //when //then
        mockMvc.perform(post("/members/add")
                .param("memberId", "testId")
                .param("memberPwd", "test")
                .param("memberNM", "test"))
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrors("member", "memberPwd"))
                .andExpect(model().attributeHasFieldErrorCode("member", "memberPwd", "Range"));
    }
}