package com.example.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.util.Log.d;

/**
 * Created by Nick on 2016/2/26.
 */
public class NewsTitleFrag extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "Nick---NewsTitleFrag";
    List<News> newsList;
    NewsAdapter newsAdapter;
    boolean isTwoPane;
    private ListView newsTitleListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        d(TAG, "NewsTitleFrag.onCreateView.newsList=="+newsList);
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        newsTitleListView = (ListView)view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(newsAdapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        newsAdapter = new NewsAdapter(activity,R.layout.news_item,newsList);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        d(TAG, "NewsTitleFrag.onActivityCreated.");
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane = true;
        }else{
            isTwoPane = false;
        }
    }

    private List<News> getNews() {
        List<News> nl = new ArrayList<News>();
        News news1 = new News();

        news1.setTitle(getResources().getString(R.string.new1_title));
        news1.setContent(getResources().getString(R.string.new1_content));
        News news2 = new News();
        news2.setTitle(getResources().getString(R.string.new2_title));
        news2.setContent(getResources().getString(R.string.new2_content));
        nl.add(news1);
        nl.add(news2);
        return nl;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        if (isTwoPane){
            NewsContentFrag ncf = (NewsContentFrag)
                    getFragmentManager().findFragmentById(R.id.news_content_fragment);
            ncf.refresh(news.getTitle(),news.getContent());
        }else{
            NewsContentActivity.startAction(getActivity(),news.getTitle(),news.getContent());
        }
    }
}
