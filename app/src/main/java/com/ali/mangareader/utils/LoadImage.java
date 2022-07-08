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

    Context context;
    Picasso picasso;

    public LoadImage(Context context) {
        this.context = context;
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Referer", "https://readmanganato.com/")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
        this.picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
    }

    public void setImage(String url, ImageView imageView, String site) {
        picasso.load(url).into(imageView);

    }

}
