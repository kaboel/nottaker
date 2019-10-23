package com.example.nottaker;

import androidx.annotation.NonNull;

public class Note {

    private String id, title, content, date, time;

    Note() {};

    Note(String title, String content, String date, String time) {

        this.title = title;
        this.content = content;
        this.date = content;
        this.time = content;

    }

    Note(String id, String title, String content, String date, String time) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.date = content;
        this.time = content;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
