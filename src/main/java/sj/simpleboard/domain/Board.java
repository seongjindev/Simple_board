package sj.simpleboard.domain;

import lombok.Data;

@Data
public class Board {
    private Long no;
    private String title;
    private String contents;
    private String date;

    public Board() {
    }

    public Board(String title, String contents, String date) {
        this.title = title;
        this.contents = contents;
        this.date = date;
    }
}
