package sj.simpleboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sj.simpleboard.domain.Board;
import sj.simpleboard.repository.BoardRepository;
import sj.simpleboard.repository.MemoryBoardRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/view")
public class BoardController {

    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/board")
    public String board(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "view/board";
    }

    @GetMapping("/add")
    public String boardForm() {
        return "view/boardAdd";
    }

    @PostMapping("/add")
    public String boardAdd(@ModelAttribute Board board) {
        boardRepository.save(board);
        return "view/boardContents";
    }


    @PostConstruct
    public void init() {
        Board board1 = new Board("글제목1", "내용1", "날짜1");
        Board board2 = new Board("글제목2", "내용2", "날짜2");
        boardRepository.save(board1);
        boardRepository.save(board2);
    }

}
