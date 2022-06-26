package com.ali.mangareader.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.mangareader.ListChapterActivity;
import com.ali.mangareader.MainActivity;
import com.ali.mangareader.adapter.ChapterListCardAdapter;
import com.ali.mangareader.model.Chapter;
import com.ali.mangareader.model.InfoManga;
import com.ali.mangareader.utils.JoinList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Info {

    InfoManga info;
    JoinList joinList;

    public void getMangaInfo(Context context, ImageView coverImage, TextView infoTitle, TextView infoAlternative,
                             TextView infoAuthor, TextView infoGenre, TextView infoStatus, TextView infoPlot,
                             Button infoChaptersBtn, ProgressDialog progressDialog, String url, String site) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://manga-scraper-api.pgamer.repl.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MangaScraperApi mangaScraperApi = retrofit.create(MangaScraperApi.class);
        Call<InfoManga> call = mangaScraperApi.getInfo(url, site);
        call.enqueue(new Callback<InfoManga>() {
            @Override
            public void onResponse(Call<InfoManga> call, Response<InfoManga> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                info = response.body();
                Toast.makeText(context, "Request Success!", Toast.LENGTH_SHORT).show();
                MainActivity.mangaReaderData.setManga(info);
                Picasso.get().load(info.getCover()).into(coverImage);
                infoTitle.setText(info.getTitle());
                // Alternative name
                if (info.getAlternativeName().isEmpty()) {
                    infoAlternative.setText("Updating");
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        infoAlternative.setText(String.join(", ", info.getAlternativeName()));
                    }
                    else {
                        infoAlternative.setText(joinList.join(info.getAlternativeName()));
                    }
                }
                // Authors
                if (info.getAuthors().isEmpty()) {
                    infoAuthor.setText("Authors: Updating");
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        infoAuthor.setText("Authors: " + String.join(", ", info.getAuthors()));
                    }
                    else {
                        infoAuthor.setText("Authors: " + joinList.join(info.getAuthors()));
                    }

                }
                // Genre
                if (info.getGenre().isEmpty()) {
                    infoGenre.setText("Genre: Updating");
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        infoGenre.setText("Genre: " + String.join(", ", info.getGenre()));
                    }
                    else {
                        infoGenre.setText("Genre" + joinList.join(info.getGenre()));

                    }

                }
                infoStatus.setText("Status: " + info.getStatus());
                if (info.getPlot().isEmpty()) {
                    infoPlot.setText("Updating");
                }
                else {
                    infoPlot.setText(info.getPlot());
                }
                String chapters = "";
                for (List<String> chapter: info.getChapters()) {
                    chapters += chapter.get(0) + "@" + chapter.get(1) + "@" + chapter.get(2) + "&&";
                }
                String finalChapters = chapters;
                infoChaptersBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, ListChapterActivity.class)
                        .putExtra("chapters", finalChapters));
                    }
                });
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<InfoManga> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

}
