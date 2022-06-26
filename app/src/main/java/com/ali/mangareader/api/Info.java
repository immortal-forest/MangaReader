package com.ali.mangareader.api;

import android.content.Context;
import android.widget.Toast;

import com.ali.mangareader.model.InfoManga;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Info {

    InfoManga info;

    public void getMangaInfo(Context context, String url, String site) {
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
                Toast.makeText(context, "Request Success!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<InfoManga> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

}
