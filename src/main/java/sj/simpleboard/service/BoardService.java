package sj.simpleboard.service;

import sj.simpleboard.domain.Board;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {

    boolean chkPwd(long boardNo, String conPwd);
    int LastCon();
    List<Board> findSubAll(Long boardNo);
    ArrayList<Integer> pageNum();
    Board pageContents();
}
