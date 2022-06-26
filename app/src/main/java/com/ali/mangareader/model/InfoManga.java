package com.ali.mangareader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoManga {
    @SerializedName("Alternative_names")
    private List<String> alternativeName;
    @SerializedName("Authors")
    private List<String> authors;
    @SerializedName("Chapters")
    private List<Chapter> chapters;
    @SerializedName("Cover")
    private String cover;
    @SerializedName("Genre")
    private List<String> genre;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Status")
    private String status;
    @SerializedName("Title")
    private String title;

    public InfoManga(List<String> alternativeName, List<String> authors, List<Chapter> chapters, String cover, List<String> genre, String plot, String status, String title) {
        this.alternativeName = alternativeName;
        this.authors = authors;
        this.chapters = chapters;
        this.cover = cover;
        this.genre = genre;
        this.plot = plot;
        this.status = status;
        this.title = title;
    }

    public List<String> getAlternativeName() {
        return alternativeName;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public String getCover() {
        return cover;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getPlot() {
        return plot;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }
}
