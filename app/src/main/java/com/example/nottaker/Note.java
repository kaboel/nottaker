package com.example.nottaker;

import androidx.annotation.NonNull;

public class Note {

    String id, title, content, date, time;

    Note() {};

    Note(String title, String content, String date, String time) {

        this.title = title;
        this.content = content;
        this.date = content;
        this.time = content;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
