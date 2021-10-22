package sj.simpleboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sj.simpleboard.domain.Board;
import sj.simpleboard.domain.Member;
import sj.simpleboard.repository.BoardRepository;
import sj.simpleboard.repository.MysqlBoardRepository;
import sj.simpleboard.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private final MysqlBoardRepository mysqlBoardRepository;

    public MemberServiceImpl(MysqlBoardRepository boardRepository) {
        this.mysqlBoardRepository = boardRepository;
    }

    public boolean join(Member member) {
        Member findMember = mysqlBoardRepository.memberFindById(member.getMemberId());
        if (findMember != null) {
            return false;
        }
        mysqlBoardRepository.memberSave(member);
        return true;
    }

    public boolean chkPwd(String memberId, String memberPwd) {
        Member member = mysqlBoardRepository.memberFindById(memberId);
        if (member == null) {
            return false;
        }
        if (!member.getMemberPwd().equals(memberPwd)) {
            return false;
        }
        return true;
    }
    public void newSession(HttpServletRequest request, Member member) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        session.setAttribute("MemberId",member.getMemberId());
    }
}
