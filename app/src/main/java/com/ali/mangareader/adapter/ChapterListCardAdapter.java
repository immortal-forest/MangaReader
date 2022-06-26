package com.ali.mangareader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ali.mangareader.R;
import com.ali.mangareader.model.Chapter;

import java.util.List;

public class ChapterListCardAdapter extends BaseAdapter {
    private List<Chapter> chapterList;
    Context context;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Chapter chapter);
    }

    public ChapterListCardAdapter(Context context, List<Chapter> chapters, OnItemClickListener listener) {
        this.chapterList = chapters;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return chapterList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView = LayoutInflater.from(context).inflate(R.layout.list_chapter_item, null);
        TextView chapterName = myView.findViewById(R.id.chapter_name);
        TextView chapterDate = myView.findViewById(R.id.chapter_date);
        chapterName.setText(chapterList.get(i).getChapterName());
        chapterName.setSelected(true);
        chapterDate.setText(chapterList.get(i).getChapterDate());
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(chapterList.get(i));
            }
        });
        return myView;
    }
}
