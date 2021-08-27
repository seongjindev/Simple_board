package sj.simpleboard.repository;

import sj.simpleboard.domain.Board;

import java.util.List;

public interface BoardRepository {

    Board save(Board board);
    Board findByNo(Long no);
    List<Board> findAll();
    void update(Long no, Board updateParam);
    boolean delete(Long no, String conPwd);
    void clearStore();
}
