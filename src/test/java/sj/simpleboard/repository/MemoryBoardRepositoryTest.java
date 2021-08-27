package sj.simpleboard.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sj.simpleboard.domain.Board;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryBoardRepositoryTest {

    BoardRepository boardRepository = new MemoryBoardRepository();

    @AfterEach //수행하고 난 뒤
    void afterEach() {
        boardRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Board board = new Board("title", "contents", "2020");
        //when
        Board saveBoard = boardRepository.save(board);
        //than
        Board findBoard = boardRepository.findByNo(board.getNo());
        assertThat(findBoard).isEqualTo(saveBoard);
    }

    @Test
    void save2() {
        //given
        Board board = new Board("title", "contents", "2020","testId", "1234");
        //when
        Board saveBoard = boardRepository.save(board);
        //than
        Board findBoard = boardRepository.findByNo(board.getNo());
        assertThat(findBoard.getConId()).isEqualTo(saveBoard.getConId());
        assertThat(findBoard.getConPwd()).isEqualTo(saveBoard.getConPwd());
    }

    @Test
    void update() {
        //given
        Board board1 = new Board("title11", "contents11", "2020");
        Board board2 = new Board("title22", "contents22", "2020");
        boardRepository.save(board1);
        boardRepository.save(board2);
        //when
        boardRepository.update(board1.getNo(), board2);
        //than
        assertThat(board1.getTitle()).isEqualTo(board2.getTitle());
        assertThat(board1.getContents()).isEqualTo(board2.getContents());
        assertThat(board1.getDate()).isNotEqualTo(board2.getDate());

    }

    @Test
    void delete() {
        //given
        Board board1 = new Board("title11", "contents11", "2020","testId1", "test");
        Board board2 = new Board("title22", "contents22", "2020", "testId2", "test2");
        boardRepository.save(board1);
        boardRepository.save(board2);
        //when
        boardRepository.delete(board1.getNo(),"test");
        //than
        assertThat(boardRepository.findAll().size()).isEqualTo(1);
        assertThat(boardRepository.findByNo(board2.getNo())).isEqualTo(board2);
    }

}