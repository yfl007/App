package com.example.fragmentbestpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nick on 2016/2/18.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private int mResourceId;
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        News news = getItem(position);
        View view;
        if (convertView!=null){
            view = convertView;
        }else{
            view = LayoutInflater.from(getContext()).inflate(mResourceId,null);
        }
        TextView news_title = (TextView)view.findViewById(R.id.news_title);
        news_title.setText(news.getTitle());
        return view;
    }
}
