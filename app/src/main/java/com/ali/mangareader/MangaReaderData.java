package com.ali.mangareader;

import com.ali.mangareader.adapter.RecentCardAdapter;
import com.ali.mangareader.model.RecentManga;

import java.util.ArrayList;
import java.util.List;

public class MangaReaderData {
    private List<RecentManga> mangas = new ArrayList<>();

    public MangaReaderData() {
        ;
    }

    public List<RecentManga> getMangas() {
        return mangas;
    }

    public void setMangas(List<RecentManga> mangas) {
        this.mangas = mangas;
    }

}
