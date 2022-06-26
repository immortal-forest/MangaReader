package com.ali.mangareader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.ali.mangareader.adapter.ChapterListCardAdapter;
import com.ali.mangareader.model.Chapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListChapterActivity extends AppCompatActivity {

    private ChapterListCardAdapter chapterListCardAdapter;
    private ListView chapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chapter);
        getSupportActionBar().setTitle("Chapter List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chapterList = findViewById(R.id.chapter_list);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String chapter = bundle.get("chapters").toString();
        List<String> list = Arrays.asList(chapter.split("&&"));
        List<Chapter> chapters = new ArrayList<>();
        for (String i: list) {
            chapters.add(new Chapter(Arrays.asList(i.split("@"))));
        }
        chapterListCardAdapter = new ChapterListCardAdapter(this, chapters, new ChapterListCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Chapter chapter) {
                System.out.println(chapter.getChapterName());
            }
        });
        chapterList.setAdapter(chapterListCardAdapter);

    }
}