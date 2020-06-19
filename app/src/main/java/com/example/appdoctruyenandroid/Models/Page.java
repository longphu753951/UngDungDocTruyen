package com.example.appdoctruyenandroid.Models;

public class Page {
    private int id;
    private int maChapter;
    private String thumbnail;

    public Page() {
    }

    public Page(int id, int maChapter, String thumbnail) {
        this.id = id;
        this.maChapter = maChapter;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaChapter() {
        return maChapter;
    }

    public void setMaChapter(int maChapter) {
        this.maChapter = maChapter;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
