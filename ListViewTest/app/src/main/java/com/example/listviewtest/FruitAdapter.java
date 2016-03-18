package com.example.listviewtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.util.Log.d;

/**
 * Created by Nick on 2016/2/1.
 */
public class FruitAdapter extends ArrayAdapter<Fruit>{
    private static final String TAG = "nick---FruitAdapter";
    private int mResourceId;

    public FruitAdapter(Context context,int resource,List<Fruit> objects){
        super(context,resource,objects);
        mResourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        d(TAG, "FruitAdapter.getView.position= " + position);
        View view;
        Fruit fruit =getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView==null){
            d(TAG, "FruitAdapter.getView.converView==null");
            view = LayoutInflater.from(getContext()).inflate(mResourceId, null);
            ImageView imageView=(ImageView)view.findViewById(R.id.item_image);
            TextView textView = (TextView)view.findViewById(R.id.item_text);
            viewHolder.imageView = imageView;
            viewHolder.textView = textView;
            view.setTag(viewHolder);
        }else {
            d(TAG, "FruitAdapter.getView.converView!=null");
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        d(TAG, "FruitAdapter.getView.viewToString="+view.toString());
        viewHolder.imageView.setImageResource(fruit.getImageId());
        viewHolder.textView.setText(fruit.getName());
        return view;
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView textView;
    }
}
