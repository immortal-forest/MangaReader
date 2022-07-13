package com.ali.mangareader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ali.mangareader.api.ChapterImages;

public class ChapterActivity extends AppCompatActivity {

    ScrollView chapterScrollView;
    LinearLayout chapterLayout;
    ChapterImages chapterImages = new ChapterImages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        ProgressDialog progress = new ProgressDialog(this, R.style.DialogTheme);
        progress.setTitle("Loading");
        progress.setMessage("Fetching images from the server");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        chapterLayout = findViewById(R.id.chapter_layout);
        chapterScrollView = findViewById(R.id.chapter_scroll_view);
        chapterScrollView.setSmoothScrollingEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("url");
        String name = bundle.getString("name");
        String site = bundle.getString("site");
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chapterImages.getChapterImage(this, chapterLayout, url, site, progress);


    }
}