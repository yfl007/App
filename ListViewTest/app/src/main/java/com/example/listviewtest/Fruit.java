package com.example.listviewtest;

/**
 * Created by Nick on 2016/2/1.
 */
public class Fruit {
    private String mName;
    private int mImageId;
    public Fruit(String name,int image_id){
        this.mName=name;
        this.mImageId=image_id;
    }
    public String getName(){
        return mName;
    }
    public int getImageId(){
        return mImageId;
    }
}
