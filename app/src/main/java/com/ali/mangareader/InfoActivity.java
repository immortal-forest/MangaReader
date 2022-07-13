package com.ali.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ali.mangareader.adapter.ChapterListCardAdapter;
import com.ali.mangareader.api.Info;
import com.ali.mangareader.model.InfoManga;
import com.ali.mangareader.utils.JoinList;

public class InfoActivity extends AppCompatActivity {

    private Info info = new Info();
    ImageView infoCover, secCover;
    TextView infoTitle, infoExtra, infoPlot;
    LinearLayout chapterList;
    ScrollView scrollView;
    RecyclerView infoGenre;
    InfoManga manga;
    JoinList joinList;
    String dot = "•";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ProgressDialog progress = new ProgressDialog(this, R.style.DialogTheme);
        progress.setTitle("Loading");
        progress.setMessage("Fetching response from the server");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6200EE")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoCover = findViewById(R.id.info_cover);
        secCover = findViewById(R.id.sec_cover);
        infoTitle = findViewById(R.id.info_title);
        infoExtra = findViewById(R.id.info_extra);
        infoPlot = findViewById(R.id.info_plot);
        chapterList = findViewById(R.id.chapter_list);
        infoGenre = findViewById(R.id.info_genre);
        scrollView = findViewById(R.id.scrollView);
        scrollView.setSmoothScrollingEnabled(true);
        Thread scrollChange = new Thread(new Runnable() {
            @Override
            public void run() {
                detectScroll();
            }
        });

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String manga_url = bundle.get("manga_url").toString();
        String site = bundle.get("site").toString();
        info.getMangaInfo(this, infoCover, secCover, infoTitle, infoExtra, infoPlot, chapterList, infoGenre, progress, manga_url, site);
        //info.getMangaInfo(this, coverImage, infoTitle, infoAlternative, infoAuthor, infoGenre,
                //infoStatus, infoPlot, infoChaptersBtn, progress, manga_url, site);
    }

    void detectScroll() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY <= oldScrollY) {
                        getSupportActionBar().show();
                    }
                    else {
                        getSupportActionBar().hide();
                    }

                }
            });
        }
    }

}