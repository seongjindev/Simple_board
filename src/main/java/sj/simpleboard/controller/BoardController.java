package sj.simpleboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sj.simpleboard.domain.Board;
import sj.simpleboard.repository.BoardRepository;
import sj.simpleboard.repository.MemoryBoardRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/view")
@Slf4j
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

    @GetMapping("/board/{boardNo}")
    public String boardDetail(@PathVariable long boardNo, Model model) {
        Board board = boardRepository.findByNo(boardNo);
        model.addAttribute("board", board);
        return "view/boardContents";
    }

    @GetMapping("/add")
    public String boardForm() {
        return "view/boardAdd";
    }

    @PostMapping("/add")
    public String boardAdd(@ModelAttribute Board board, Model model) {
        boardRepository.save(board);
        model.addAttribute("board", board);
        return "redirect:/view/board/" + board.getNo();
    }

    @GetMapping("/{boardNo}/edit")
    public String boardEditForm(@PathVariable long boardNo, Model model) {
        Board board = boardRepository.findByNo(boardNo);
        model.addAttribute("board", board);
        return "view/boardEdit";
    }

    @PostMapping("/{boardNo}/edit")
    public String edit(@PathVariable long boardNo,
                        @ModelAttribute Board board
    ) {
        Board beBoard = boardRepository.findByNo(boardNo);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
        beBoard.setTitle(board.getTitle());
        beBoard.setContents(board.getContents());
        beBoard.setDate(now);
        return "redirect:/view/board/{boardNo}";
    }

    @PostMapping("/{boardNo}/delete")
    public String delete(@PathVariable long boardNo,
                         @RequestParam String conPwd,
                         RedirectAttributes redirectAttributes
    ) {
        boolean delete = boardRepository.delete(boardNo, conPwd);
        if(delete) {
            return "redirect:/view/board";
        }else {
            redirectAttributes.addAttribute("status", false);
            return "redirect:/view/{boardNo}/edit";
        }
    }


    @PostConstruct
    public void init() {
        Board board1 = new Board("글제목1", "내용1", "날짜1","testId1", "1234");
        Board board2 = new Board("글제목2", "내용2", "날짜2", "testId2", "2345");
        boardRepository.save(board1);
        boardRepository.save(board2);
    }

}
