package com.example.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.util.Log.d;

/**
 * Created by Nick on 2016/2/16.
 */
public class RightFragment extends Fragment {
    private static final String TAG = "nick---RightFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        d(TAG, "RightFragment.onCreateView.");
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.right_fragment,container,false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        d(TAG, "RightFragment.onAttach.");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        d(TAG, "RightFragment.onActivityCreated.");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d(TAG, "RightFragment.onCreate.");
    }

    @Override
    public void onStart() {
        super.onStart();
        d(TAG, "RightFragment.onStart.");
    }

    @Override
    public void onResume() {
        super.onResume();
        d(TAG, "RightFragment.onResume.");
    }

    @Override
    public void onPause() {
        super.onPause();
        d(TAG, "RightFragment.onPause.");
    }

    @Override
    public void onStop() {
        super.onStop();
        d(TAG, "RightFragment.onStop.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        d(TAG, "RightFragment.onDestroyView.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        d(TAG, "RightFragment.onDestroy.");
    }
}
