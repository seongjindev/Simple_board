package sj.simpleboard.repository;

import sj.simpleboard.domain.Board;

import java.util.List;

public interface BoardRepository {

    void save(Board board);
    Board findByNo(Long no);
    Board findNum();
    List<Board> findAll();
    void update(Long no, Board updateParam);
    void delete(Long no);
    void clearStore();
}
