package com.ali.mangareader.model;

import java.util.List;

public class Chapter {

    private String chapterName;
    private String chapterUrl;
    private String chapterDate;

    public Chapter(List<String> chapter) {
        this.chapterName = chapter.get(0);
        this.chapterUrl = chapter.get(1);
        this.chapterDate = chapter.get(2);
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public String getChapterDate() {
        return chapterDate;
    }
}
