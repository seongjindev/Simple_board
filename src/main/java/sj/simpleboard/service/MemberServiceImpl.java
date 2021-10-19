package sj.simpleboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sj.simpleboard.domain.Board;
import sj.simpleboard.domain.Member;
import sj.simpleboard.repository.BoardRepository;
import sj.simpleboard.repository.MysqlBoardRepository;

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

    public boolean chkLogin(String memberId, String memberPwd) {
        Member member = mysqlBoardRepository.memberFindById(memberId);
        if (member == null) {
            return false;
        }
        if (member.getMemberPwd().equals(memberPwd)) {
            return true;
        }
        return false;
    }
}
