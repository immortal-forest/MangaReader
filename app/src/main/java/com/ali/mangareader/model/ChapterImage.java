package com.ali.mangareader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChapterImage {
    @SerializedName("Urls")
    private List<String> urls;

    public ChapterImage(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }
}
