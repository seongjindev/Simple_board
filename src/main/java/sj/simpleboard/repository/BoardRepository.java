package sj.simpleboard.repository;

import sj.simpleboard.domain.Board;

public interface BoardRepository {

    Board save(Board board);
    Board findByNo(Long no);
    void update(Long no, Board updateParam);
    void clearStore();
}
