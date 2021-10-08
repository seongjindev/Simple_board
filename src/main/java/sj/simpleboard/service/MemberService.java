package sj.simpleboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sj.simpleboard.domain.Board;
import sj.simpleboard.domain.Member;
import sj.simpleboard.repository.BoardRepository;
import sj.simpleboard.repository.MysqlBoardRepository;

@Slf4j
@Service
public class MemberService {

    private final BoardRepository boardRepository;

    public MemberService(MysqlBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public boolean join(Member member) {
        Member findMember = boardRepository.memberFindById(member.getMemberId());
        if (findMember != null) {
            return false;
        }
        boardRepository.memberSave(member);
        return true;
    }
}
