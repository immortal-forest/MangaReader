package com.ali.mangareader.api;

import static android.widget.ImageView.*;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Range;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ali.mangareader.model.ChapterImage;
import com.ali.mangareader.utils.LoadImage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChapterImages {

    List<String> chapters;

    public void getChapterImage(Context context, LinearLayout chapterLayout, String url, String site, ProgressDialog progress) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://manga-scraper-api.pgamer.repl.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MangaScraperApi mangaScraperApi = retrofit.create(MangaScraperApi.class);
        Call<ChapterImage> call = mangaScraperApi.getChapterImages(url, site);
        call.enqueue(new Callback<ChapterImage>() {
            @Override
            public void onResponse(Call<ChapterImage> call, Response<ChapterImage> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                chapters = response.body().getUrls();
                Thread setImageThread = new Thread() {
                    @Override
                    public void run() {
                        setImages(context, chapters, chapterLayout, site, progress);
                    }
                };
                setImageThread.run();

            }

            @Override
            public void onFailure(Call<ChapterImage> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    void setImages(Context context, List<String> chapters, LinearLayout chapterLayout, String site, ProgressDialog progress) {
        //List<ImageView> imageViews = new ArrayList<>();
        int i = 0;
        LoadImage loadImage = new LoadImage(context);
        while (i < chapters.size()) {
            ImageView imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
            //imageViews.add(imageView);
            chapterLayout.addView(imageView);
            loadImage.setImage(chapters.get(i), imageView, site);
            i++;
        }
        progress.dismiss();
    }

}
