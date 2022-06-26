package com.ali.mangareader.model;

import com.google.gson.annotations.SerializedName;

public class RecentManga {
    @SerializedName("Cover")
    private String cover;
    @SerializedName("Latest_Chapter")
    private String latestChapter;
    @SerializedName("Title")
    private String title;
    @SerializedName("Url")
    private String url;
    private int id;

    public RecentManga(String cover, String latestChapter, String title, String url, int id) {
        this.cover = cover;
        this.latestChapter = latestChapter;
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public String getLatestChapter() {
        return latestChapter;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }
}
