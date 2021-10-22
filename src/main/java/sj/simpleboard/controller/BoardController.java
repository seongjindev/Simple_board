package sj.simpleboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sj.simpleboard.domain.Board;
import sj.simpleboard.repository.MysqlBoardRepository;
import sj.simpleboard.service.BoardServiceImpl;
import sj.simpleboard.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/view")
@Slf4j
public class BoardController {

    private final MysqlBoardRepository mysqlBoardRepository;
    private final BoardServiceImpl boardServiceImpl;

    //생성자 주입 - 생성자가 1개일경우에는 @Autowired를 안써도 된다
    public BoardController(MysqlBoardRepository mysqlBoardRepository, BoardServiceImpl boardServiceImpl) {
//    public BoardController(MemoryBoardRepository boardRepository, BoardService boardService) {
        this.mysqlBoardRepository = mysqlBoardRepository;
        this.boardServiceImpl = boardServiceImpl;
    }

    @GetMapping("/board/{boardNo}")
    public String board(@PathVariable long boardNo, Model model) {
        List<Board> boards = boardServiceImpl.findSubAll(boardNo);
        int maxCon = boardServiceImpl.LastCon();
        model.addAttribute("boards", boards);
        model.addAttribute("maxCon", maxCon);
        model.addAttribute("boardNo", boardNo);
        return "view/board";
    }

    @GetMapping("/board/{boardNo}/{detailNo}")
    public String boardDetail(@PathVariable long boardNo, @PathVariable long detailNo, Model model) {
        Board board = mysqlBoardRepository.boardFindByNo(detailNo);
        model.addAttribute("board", board);
        model.addAttribute("boardNo", boardNo);
        return "view/boardContents";
    }

    @GetMapping("/add")
    public String boardForm() {
        return "view/boardAdd";
    }

    @PostMapping("/add")
    public String boardAdd(@ModelAttribute Board board, Model model, HttpServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        String memberId = (String)session.getAttribute("MemberId");
        board.setConId(memberId);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
        board.setConDate(now);
        mysqlBoardRepository.boardSave(board);
        model.addAttribute("board", board);
        Board num = mysqlBoardRepository.boardFindNum();
        return "redirect:/view/board/1/" + num.getSeq();
    }

    @GetMapping("/board/{boardNo}/edit/{detailNo}")
    public String boardEditForm(@PathVariable long boardNo, @PathVariable long detailNo, Model model) {
        Board board = mysqlBoardRepository.boardFindByNo(detailNo);
        model.addAttribute("board", board);
        model.addAttribute("boardNo",boardNo);
        return "view/boardEdit";
    }

    @PostMapping("/board/{boardNo}/edit/{detailNo}")
    public String edit(@PathVariable long boardNo,
                       @PathVariable long detailNo,
//                        @RequestParam String conPwd,
                        @ModelAttribute Board board,
                       RedirectAttributes redirectAttributes
    ) {
//        boolean chkPwd = boardServiceImpl.chkPwd(detailNo, conPwd);
//        if (chkPwd) {
//            redirectAttributes.addAttribute("status", false);
//            return "redirect:/view/board/{boardNo}/edit/{detailNo}";
//        }
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
        board.setConDate(now);
        mysqlBoardRepository.boardUpdate(detailNo, board);
        return "redirect:/view/board/{boardNo}/{detailNo}";

    }

    @PostMapping("/board/{boardNo}/delete/{detailNo}")
    public String delete(@PathVariable long boardNo,
                         @PathVariable long detailNo,
//                         @RequestParam String conPwd,
                         RedirectAttributes redirectAttributes
    ) {
//        boolean chkPwd = boardServiceImpl.chkPwd(detailNo, conPwd);
//        if(chkPwd) {
//            redirectAttributes.addAttribute("status", false);
//            return "redirect:/view/board/{boardNo}/edit/{detailNo}";
//        }
        mysqlBoardRepository.boardDelete(detailNo);
        return "redirect:/view/board/1";
    }



//    @PostConstruct
//    public void init() {
//        Board board1 = new Board("글제목1", "내용1", "날짜1","testId1", "1234");
//        Board board2 = new Board("글제목2", "내용2", "날짜2", "testId2", "2345");
//        boardRepository.save(board1);
//        boardRepository.save(board2);
//    }

}
