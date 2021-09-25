package sj.simpleboard.domain;

import lombok.Data;

@Data
public class Board {
    private Long seq;
    private String title;
    private String contents;
    private String conDate;
    private String conId;
    private String conPwd;

    public Board() {
    }

    public Board(String title, String contents, String date) {
        this.title = title;
        this.contents = contents;
        this.conDate = date;
    }

    public Board(String title, String contents, String date, String conId, String conPwd) {
        this.title = title;
        this.contents = contents;
        this.conDate = date;
        this.conId = conId;
        this.conPwd = conPwd;
    }
}
