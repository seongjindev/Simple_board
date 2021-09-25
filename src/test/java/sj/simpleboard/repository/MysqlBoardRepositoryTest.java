package sj.simpleboard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sj.simpleboard.domain.Board;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MysqlBoardRepositoryTest {

    @Autowired MysqlBoardRepository mysqlBoardRepository;

//    void save(Board board);
//    Board findByNo(Long no);
//    Board findNum();
//    List<Board> findAll();
//    void update(Long no, Board updateParam);
//    void delete(Long no);

    @Test
    @Transactional
    void save() {
        //given
        Board board = new Board("title", "contents", "2020");
        //when
        mysqlBoardRepository.save(board);
        Board num = mysqlBoardRepository.findNum();
        board.setSeq(num.getSeq());
        //than
        Board findBoard = mysqlBoardRepository.findByNo(num.getSeq());
        assertThat(findBoard).isEqualTo(board);
    }
}