package sj.simpleboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sj.simpleboard.domain.Board;
import sj.simpleboard.repository.BoardRepository;
import sj.simpleboard.repository.MysqlBoardRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final MysqlBoardRepository mysqlBoardRepository;

    public BoardServiceImpl(MysqlBoardRepository mysqlBoardRepository) {
        this.mysqlBoardRepository = mysqlBoardRepository;
    }

    @Override
    public boolean chkPwd(long boardNo, String conPwd) {
        Board findBoard = mysqlBoardRepository.boardFindByNo(boardNo);
        if(findBoard.getConPwd().equals(conPwd)) {
            return false;
        }
        return true;
    };

    @Override
    public int LastCon() {
        int LastCon = (int)Math.ceil(mysqlBoardRepository.LastCon()/10.0);
        return LastCon;
    }

    @Override
    public List<Board> findSubAll(Long boardNo) {
        List<Board> boards = mysqlBoardRepository.boardFindSubAll((boardNo-1)*10, 10L);
        return boards;
    }

    @Override
    public ArrayList<Integer> pageNum() {
        return null;
    }

    @Override
    public Board pageContents() {
        return null;
    }
}
