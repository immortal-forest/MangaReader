package com.ali.mangareader.api;

import com.ali.mangareader.model.ChapterImage;
import com.ali.mangareader.model.InfoManga;
import com.ali.mangareader.model.RecentManga;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MangaScraperApi {

    @GET("recent")
    Call<List<RecentManga>> getRecentUpdates(@Query("limit") String limit, @Query("site") String site);

    @GET("info")
    Call<InfoManga> getInfo(@Query("url") String url, @Query("site") String site);

    @GET("chapter-imgs")
    Call<ChapterImage> getChapterImages(@Query("url") String url, @Query("site") String site);
}
