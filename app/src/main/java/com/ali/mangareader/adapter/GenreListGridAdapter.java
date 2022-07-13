package com.ali.mangareader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ali.mangareader.R;

import java.util.List;

public class GenreListGridAdapter extends RecyclerView.Adapter<GenreListGridAdapter.GenreHolder> {


    public interface  OnItemClickListener {
        void onItemClick(String name);
    }

    private Context context;
    private List<String> genreList;
    private OnItemClickListener listener;

    public GenreListGridAdapter(Context context, List<String> genreList, OnItemClickListener listener) {
        this.context = context;
        this.genreList = genreList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item, parent, false);
        return new GenreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {
        holder.setGenreName(genreList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }


    public static class GenreHolder extends RecyclerView.ViewHolder {
        private TextView genreName;
        private View view;


        public GenreHolder(@NonNull View view) {
            super(view);
            this.view = view;
            genreName = view.findViewById(R.id.genre_name);
        }

        private void setGenreName(String name, OnItemClickListener listener) {
            genreName.setText(name);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(name);
                }
            });
        }
    }

}
