package com.ali.mangareader.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class LoadImage {

    public void setImage(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }

}
