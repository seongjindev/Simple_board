package sj.simpleboard.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sj.simpleboard.domain.Board;
import sj.simpleboard.service.BoardService;

import static org.assertj.core.api.Assertions.*;

class MemoryBoardRepositoryTest {

    BoardRepository boardRepository = new MemoryBoardRepository();
    BoardService boardService = new BoardService(boardRepository);

    @AfterEach //수행하고 난 뒤
    void afterEach() {
        boardRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Board board = new Board("title", "contents", "2020");
        //when
        boardRepository.save(board);
        //than
        Board findBoard = boardRepository.findByNo(board.getSeq());
        assertThat(findBoard).isEqualTo(board);
    }

    @Test
    void save2() {
        //given
        Board board = new Board("title", "contents", "2020","testId", "1234");
        //when
        boardRepository.save(board);
        //than
        Board findBoard = boardRepository.findByNo(board.getSeq());
        assertThat(findBoard.getConId()).isEqualTo(board.getConId());
        assertThat(findBoard.getConPwd()).isEqualTo(board.getConPwd());
    }

    @Test
    void update() {
        //given
        Board board1 = new Board("title11", "contents11", "2020");
        Board board2 = new Board("title22", "contents22", "2020");
        boardRepository.save(board1);
        boardRepository.save(board2);
        //when
        boardRepository.update(board1.getSeq(), board2);
        //than
        assertThat(board1.getTitle()).isEqualTo(board2.getTitle());
        assertThat(board1.getContents()).isEqualTo(board2.getContents());
        assertThat(board1.getConDate()).isEqualTo(board2.getConDate());

    }

    @Test
    void delete() {
        //given
        Board board1 = new Board("title11", "contents11", "2020","testId1", "test");
        Board board2 = new Board("title22", "contents22", "2020", "testId2", "test2");
        boardRepository.save(board1);
        boardRepository.save(board2);
        //when
        String testPwd = "test";
        boolean chkPwd = boardService.chkPwd(board1.getSeq(), testPwd);
        if(chkPwd) {
            boardRepository.delete(board1.getSeq());
        }
        //than
        assertThat(chkPwd).isEqualTo(true);
        assertThat(boardRepository.findAll().size()).isEqualTo(1);
        assertThat(boardRepository.findByNo(board2.getSeq())).isEqualTo(board2);
    }

}