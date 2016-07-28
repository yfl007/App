package nick.example.simplelauncher;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;

import nick.example.simplelauncher.flip.FlipViewController;


/**
 * Author: Yefenglin on 2016/6/22  14:20
 * E-mail: ye_fenglin@163.com
 */
public class PageFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<AppModel>> {
    private static final String TAG = "nick--PageFragment";
    private Context mContext;
    private PageAdapter mPageAdapter;
    protected FlipViewController mFlipView;
    private int mTotalItem = 0;
    final private Handler mHandler = new Handler();

    final private Runnable mRequestFocus = new Runnable() {
        public void run() {
            mFlipView.focusableViewAvailable(mFlipView);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mPageAdapter = new PageAdapter(mContext);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mFlipView = new FlipViewController(mContext);
//        mFlipView.setAnimationBitmapFormat(Bitmap.Config.RGB_565);
        mFlipView.setAdapter(mPageAdapter);
        return mFlipView;
    }

    @Override
    public Loader<ArrayList<AppModel>> onCreateLoader(int id, Bundle bundle) {
        return new AppsLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<AppModel>> loader, ArrayList<AppModel> apps) {
        Log.d(TAG, "onLoadFinished apps.size()=="+apps.size());
        mTotalItem = apps.size();
        if (mTotalItem==0){
            return;
        }

        mPageAdapter.addData(apps);
        if (mPageAdapter != null) {
//            ListAdapter adapter = mPageAdapter;
           // mPageAdapter = null;
            mFlipView.setAdapter(mPageAdapter);
        } /*else {
            // We are starting without an adapter, so assume we won't
            // have our data right away and start with the progress indicator.
            if (mProgressContainer != null) {
                setGridShown(false, false);
            }
        }*/
        mHandler.post(mRequestFocus);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<AppModel>> loader) {

        mPageAdapter.addData(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mFlipView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mFlipView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFlipView = null;
    }
}
