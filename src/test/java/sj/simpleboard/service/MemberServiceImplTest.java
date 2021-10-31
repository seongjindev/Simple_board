package sj.simpleboard.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sj.simpleboard.controller.LoginController;
import sj.simpleboard.domain.Member;
import sj.simpleboard.repository.MysqlBoardRepository;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Autowired
    MysqlBoardRepository mysqlBoardRepository;


    @Test
    @Transactional
    void 중복아이디_확인()  {
        //given
        Member member = new Member("test1", "test2", "test3");
        Member member2 = new Member("test1", "test2", "test3");
        //when
        boolean join = memberServiceImpl.join(member);
        boolean join2 = memberServiceImpl.join(member2);
        //then
        Assertions.assertThat(join2).isFalse();
    }

    @Test
    @Transactional
    public void 아이디_비밀번호_확인() throws Exception {
        //given
        String memberId1 = "test1";
        String memberPwd1 = "test1";
        String memberId2 = "test2";
        String memberPwd2 = "test2";
        Member member1 = new Member(memberId1, memberPwd1, "test3");
        Member member2 = new Member(memberId2, memberPwd2, "test3");
        memberServiceImpl.join(member1);
        memberServiceImpl.join(member2);
        //when
        Member FindMember1 = mysqlBoardRepository.memberFindById(memberId1);
        Member FindMember2 = mysqlBoardRepository.memberFindById(memberId2);
        boolean chkIdPwd1 = memberServiceImpl.chkPwd(memberId1, FindMember1.getMemberPwd());
        boolean chkIdPwd2 = memberServiceImpl.chkPwd(memberId2, memberPwd2);
        boolean chkIdPwd3 = memberServiceImpl.chkPwd("none","aaaaa");

        //then
        Assertions.assertThat(chkIdPwd1).isEqualTo(true);
        Assertions.assertThat(chkIdPwd2).isEqualTo(false);
        Assertions.assertThat(chkIdPwd3).isEqualTo(false);

    }

}