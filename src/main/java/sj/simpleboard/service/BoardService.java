package sj.simpleboard.service;

import sj.simpleboard.domain.Board;

import java.util.ArrayList;

public interface BoardService {

    boolean chkPwd(long boardNo, String conPwd);
    int maxConCnt();
    ArrayList<Integer> pageNum();
    Board pageContents();
}
