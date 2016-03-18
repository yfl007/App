package com.example.fragmentbestpractice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nick on 2016/2/26.
 */
public class NewsContentFrag extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }
    public void refresh(String title,String content){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitle = (TextView)view.findViewById(R.id.news_title);
        newsTitle.setText(title);
        TextView newsContent = (TextView)view.findViewById(R.id.news_content);
        newsContent.setText(content);
    }
}
