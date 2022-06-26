package com.ali.mangareader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ali.mangareader.adapter.ChapterListCardAdapter;
import com.ali.mangareader.api.Info;
import com.ali.mangareader.model.InfoManga;
import com.ali.mangareader.utils.JoinList;

public class InfoActivity extends AppCompatActivity {

    private Info info = new Info();
    ImageView coverImage;
    TextView infoTitle, infoAlternative, infoAuthor, infoGenre, infoStatus, infoPlot;
    InfoManga manga;
    JoinList joinList;
    Button infoChaptersBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Fetching response from the server");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        getSupportActionBar().setTitle("Info");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6200EE")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coverImage = findViewById(R.id.info_cover);
        infoTitle = findViewById(R.id.info_title);
        infoAlternative = findViewById(R.id.info_alternative);
        infoAuthor = findViewById(R.id.info_author);
        infoGenre = findViewById(R.id.info_genre);
        infoStatus = findViewById(R.id.info_status);
        infoPlot = findViewById(R.id.info_plot);
        infoChaptersBtn = findViewById(R.id.chapter_btn);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String manga_url = bundle.get("manga_url").toString();
        String site = bundle.get("site").toString();
        info.getMangaInfo(this, coverImage, infoTitle, infoAlternative, infoAuthor, infoGenre,
                infoStatus, infoPlot, infoChaptersBtn, progress, manga_url, site);


    }

}