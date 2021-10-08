package sj.simpleboard.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sj.simpleboard.domain.Member;
import sj.simpleboard.repository.MysqlBoardRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void 중복아이디_확인()  {
        //given
        Member member = new Member("test1", "test2", "test3");
        Member member2 = new Member("test1", "test2", "test3");
        //when
        boolean join = memberService.join(member);
        boolean join2 = memberService.join(member2);
        //then
        Assertions.assertThat(join2).isFalse();
    }

}