package com.ali.mangareader.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ali.mangareader.R;
import com.ali.mangareader.model.Chapter;
import com.ali.mangareader.model.InfoManga;

import java.util.List;

public class InfoCardAdapter extends BaseAdapter {
    private List<Chapter> chapterList;

    InfoCardAdapter(List<Chapter> chapters) {
        this.chapterList = chapters;
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
        View myView = LayoutInflater.from(view.getContext()).inflate(R.layout.info_chapter_item, null);
        TextView chapterName = myView.findViewById(R.id.chapter_name);
        TextView chapterDate = myView.findViewById(R.id.chapter_date);
        chapterName.setText(chapterList.get(i).getChapterName());
        chapterDate.setText(chapterList.get(i).getChapterDate());
        return myView;
    }
}
