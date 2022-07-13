package com.ali.mangareader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.mangareader.R;
import com.ali.mangareader.model.RecentManga;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecentCardAdapter extends RecyclerView.Adapter<RecentCardAdapter.RecentMangaHolder> {


    private Context context;
    private List<RecentManga> mangas;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(RecentManga manga);
    }

    public RecentCardAdapter(Context context, List<RecentManga> mangas, OnItemClickListener listener) {
        this.context = context;
        this.mangas = mangas;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecentMangaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_list_item, parent, false);
        return new RecentMangaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentMangaHolder holder, int position) {
        holder.setData(mangas.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return mangas.size();
    }


    public static class RecentMangaHolder extends RecyclerView.ViewHolder {
        private ImageView cover_image;
        private TextView title, latest_chapter, manga_url;
        private View view;

        RecentMangaHolder(View view) {
            super(view);
            this.view = view;
            cover_image = view.findViewById(R.id.cover_image);
            title = view.findViewById(R.id.title);
            manga_url = view.findViewById(R.id.manga_url);
            latest_chapter = view.findViewById(R.id.latest_chapter);
        }

        private void setData(RecentManga manga, OnItemClickListener listener) {
            Picasso.get().load(manga.getCover()).into(cover_image);
            title.setText(manga.getTitle());
            title.setSelected(true);
            latest_chapter.setText(manga.getLatestChapter());
            //latest_chapter.setSelected(true);
            manga_url.setText(manga.getUrl() + ".." + manga.getId());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(manga);
                }
            });
        }


    }



}
