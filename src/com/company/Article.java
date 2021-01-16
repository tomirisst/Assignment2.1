package com.company;

import java.time.LocalDateTime;
import java.util.Date;

public class Article {
    int id;
    String source_id;
    String source_name;
    String title;
    String content;
    String date;

    private Article(int id, String source_id, String source_name, String title, String content, String date) {
        this.id = id;
        this.source_id = source_id;
        this.source_name = source_name;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public static Article parse(String[] data) {
        int id = Integer.parseInt(data[0]);
        String source_id = data[1];
        String source_name = data[2];
        String title = data[3];
        String content = data[4];
        String date = data[5];
        return new Article(id, source_id, source_name, title, content, date);
    }

    @Override
    public String toString() {
        return "" + id + ", " + source_id + ", " + source_name + ", " + title + ", " + content + ", " + date;
    }

    public String getSource_id() {
        return source_id;
    }

    public String getSource_name() {
        return source_name;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}

