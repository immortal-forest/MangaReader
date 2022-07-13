package com.ali.mangareader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.ali.mangareader.adapter.RecentCardAdapter;
import com.ali.mangareader.api.Recent;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecentCardAdapter recentCardAdapter;
    private Recent recent = new Recent();
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressDialog progress = new ProgressDialog(this, R.style.DialogTheme);
        progress.setTitle("Loading");
        progress.setMessage("Fetching response from the server");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        getSupportActionBar().setTitle("Latest Updates");
        drawerLayout = findViewById(R.id.nav_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6200EE")));
        initializeCardView(progress);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void initializeCardView(ProgressDialog progress) {
        recyclerView = findViewById(R.id.recent_manga);
        recent.getRecentManga(this, recyclerView, progress, "3", "manganato");


    }


}