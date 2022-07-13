package com.ali.mangareader.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.mangareader.ChapterActivity;
import com.ali.mangareader.R;
import com.ali.mangareader.adapter.ChapterListCardAdapter;
import com.ali.mangareader.adapter.GenreListGridAdapter;
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
    private ChapterListCardAdapter chapterListCardAdapter;
    private GenreListGridAdapter genreListGridAdapter;

    public void getMangaInfo(Context context, ImageView coverImage, ImageView secCover, TextView infoTitle,
                             TextView infoExtra, TextView infoPlot, LinearLayout chapterList, RecyclerView infoGenre,
                             ProgressDialog progressDialog, String url, String site) {
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
                Picasso.get().load(info.getCover()).into(coverImage);
                Picasso.get().load(info.getCover()).into(secCover);
                infoTitle.setText(info.getTitle());
                // Extra
                String extra = "";
                if (info.getAuthors().isEmpty()) {
                   extra = "Updating" + "  •  " + info.getStatus();
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        extra =  String.join(", ", info.getAuthors()) + "  •  " + info.getStatus();
                    }
                    else {
                        extra = joinList.join(info.getAuthors()) + "•" + info.getStatus();
                    }

                }
                infoExtra.setText(extra);
                // Plot
                if (info.getPlot().isEmpty()) {
                    infoPlot.setText("Updating" + "\n\n");
                }
                else {
                    infoPlot.setText(info.getPlot() + "\n\n");
                }
                String alName = "<b>Alternative Names:</b> ";
                if (info.getAlternativeName().isEmpty()) {
                    alName += "Updating";
                }
                else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        alName += String.join(", ", info.getAlternativeName());
                    }
                    else {
                        alName += joinList.join(info.getAlternativeName());
                    }
                }
                infoPlot.append(Html.fromHtml(alName));
                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                infoGenre.setLayoutManager(layoutManager);
                genreListGridAdapter = new GenreListGridAdapter(context, info.getGenre(), new GenreListGridAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String name) {
                        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                    }
                });
                infoGenre.setAdapter(genreListGridAdapter);
//                genreListGridAdapter = new GenreListGridAdapter(context, info.getGenre(), new GenreListGridAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(String name) {
//                        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
//                    }
//                });
//                infoGenre.setAdapter(genreListGridAdapter);
                List<Chapter> chaptersList = new ArrayList<>();
                for (List<String> i: info.getChapters()) {
                    chaptersList.add(new Chapter(i));
                }

                for (Chapter i: chaptersList) {
                    View vi = LayoutInflater.from(context).inflate(R.layout.list_chapter_item, null);
                    TextView chapterName = vi.findViewById(R.id.chapter_name);
                    chapterName.setText(i.getChapterName());
                    TextView chapterDate = vi.findViewById(R.id.chapter_date);
                    chapterDate.setText(i.getChapterDate());
                    vi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            context.startActivity(new Intent(context, ChapterActivity.class)
                                .putExtra("url", i.getChapterUrl())
                                .putExtra("name", i.getChapterName())
                                .putExtra("site", "manganato"));
                        }
                    });
                    chapterList.addView(vi);
                }
//
//                chapterListCardAdapter = new ChapterListCardAdapter(context, chaptersList, new ChapterListCardAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(Chapter chapter) {
//                        context.startActivity(new Intent(context, ChapterActivity.class)
//                                .putExtra("url", chapter.getChapterUrl())
//                                .putExtra("name", chapter.getChapterName())
//                                .putExtra("site", "manganato"));
//                    }
//                });
//                chapterList.setAdapter(chapterListCardAdapter);

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
