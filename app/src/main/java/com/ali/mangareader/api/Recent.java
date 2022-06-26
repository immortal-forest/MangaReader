package com.ali.mangareader.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.mangareader.InfoActivity;
import com.ali.mangareader.MainActivity;
import com.ali.mangareader.MangaReaderData;
import com.ali.mangareader.R;
import com.ali.mangareader.adapter.RecentCardAdapter;
import com.ali.mangareader.model.RecentManga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Recent {

    List<RecentManga> data = new ArrayList<>();
    private RecentCardAdapter recentCardAdapter;

    public void getRecentManga(Context context, RecyclerView recyclerView, ProgressDialog progress, String limit, String site) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://manga-scraper-api.pgamer.repl.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MangaScraperApi mangaScraperApi = retrofit.create(MangaScraperApi.class);
        Call<List<RecentManga>> call = mangaScraperApi.getRecentUpdates(limit, site);
        call.enqueue(new Callback<List<RecentManga>>() {
            @Override
            public void onResponse(Call<List<RecentManga>> call, Response<List<RecentManga>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                data = response.body();
                Toast.makeText(context, "Request Success!", Toast.LENGTH_SHORT).show();
                MainActivity.mangaReaderData.setMangas(data);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recentCardAdapter = new RecentCardAdapter(context, data, new RecentCardAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecentManga manga) {
                        context.startActivity(new Intent(context, InfoActivity.class)
                                .putExtra("manga_url", manga.getUrl())
                                .putExtra("site", "manganato"));
                    }
                });
                recyclerView.setAdapter(recentCardAdapter);
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<List<RecentManga>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}
