package com.ali.mangareader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.mangareader.adapter.RecentCardAdapter;
import com.ali.mangareader.api.Recent;
import com.ali.mangareader.model.RecentManga;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecentCardAdapter recentCardAdapter;
    private Recent recent = new Recent();
    public static MangaReaderData mangaReaderData = new MangaReaderData();
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Latest Updates");
        drawerLayout = findViewById(R.id.nav_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6200EE")));
        initializeCardView();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void initializeCardView() {
        recyclerView = findViewById(R.id.recent_manga);
        recent.getRecentManga(this, "1", "manganato");
        while (!mangaReaderData.getMangas().isEmpty()) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recentCardAdapter = new RecentCardAdapter(this, mangaReaderData.getMangas(), new RecentCardAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(RecentManga manga) {
                    System.out.println(manga.getTitle());
                }
            });
            recyclerView.setAdapter(recentCardAdapter);
        }


    }


}