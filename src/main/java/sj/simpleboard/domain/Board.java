package sj.simpleboard.domain;

import lombok.Data;

@Data
public class Board {
    private Long no;
    private String title;
    private String contents;
    private String date;
    private String conId;
    private String conPwd;

    public Board() {
    }

    public Board(String title, String contents, String date) {
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public Board(String title, String contents, String date, String conId, String conPwd) {
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.conId = conId;
        this.conPwd = conPwd;
    }
}
