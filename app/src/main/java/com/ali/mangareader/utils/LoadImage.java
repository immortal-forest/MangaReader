package com.ali.mangareader.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadImage {

    public void setImage(Context context, String url, ImageView imageView, String site) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = null;
                if (site == "manganato") {
                     newRequest = chain.request().newBuilder()
                            .addHeader("Referer", "https://readmanganato.com/")
                            .addHeader("sec-ch-ua-platform", "Android")
                            .build();
                }
                if (site == "mangakakalot") {
                    newRequest = chain.request().newBuilder()
                            .addHeader("Referer", "https://mangakakalot.com/")
                            .addHeader("sec-ch-ua-platform", "Android")
                            .build();

                }
                return chain.proceed(newRequest);
            }
        });

        Picasso picasso = new Picasso.Builder(context).downloader(new OkHttp3Downloader(okHttpClient)).build();
        picasso.load(url).into(imageView);

    }

}
