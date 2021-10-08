package sj.simpleboard.repository;

import sj.simpleboard.domain.Board;
import sj.simpleboard.domain.Member;

import java.util.List;

public interface BoardRepository {

    void boardSave(Board board);
    Board boardFindByNo(Long no);
    Board boardFindNum();
    List<Board> boardFindAll();
    void boardUpdate(Long no, Board updateParam);
    void boardDelete(Long no);
    void clearStore();
    void memberSave(Member member);
    Member memberFindById(String id);
}
