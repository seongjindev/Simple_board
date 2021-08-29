package sj.simpleboard.service;

import org.springframework.stereotype.Service;
import sj.simpleboard.domain.Board;
import sj.simpleboard.repository.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public boolean chkPwd(long boardNo, String conPwd) {
        Board findBoard = boardRepository.findByNo(boardNo);
        if(findBoard.getConPwd().equals(conPwd)) {
            return true;
        }
        return false;
    };

}
