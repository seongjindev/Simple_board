package sj.simpleboard.domain;

import lombok.Data;

@Data
public class board {
    private Long no;
    private String title;
    private String contents;
    private String date;

    public board() {
    }

    public board(String title, String contents, String date) {
        this.title = title;
        this.contents = contents;
        this.date = date;
    }
}
