package nick.example.simplelauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static android.util.Log.d;

/**
 * Author: Yefenglin on 2016/6/22  14:20
 * E-mail: ye_fenglin@163.com
 */
public class PageAdapter extends BaseAdapter {
    private static final String TAG = "nick--PageAdapter";
    private static final int MAX_NUM_ITEMS_OF_PAGE = 6;
    private ArrayList<AppModel> mApps;
    private int mTotalItem = 0;
    private int numOfPage = 0;
    private LayoutInflater mInflater;

    public PageAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (mApps != null) {
            mTotalItem = mApps.size();
        }
        numOfPage = (mTotalItem % MAX_NUM_ITEMS_OF_PAGE == 0) ? mTotalItem / MAX_NUM_ITEMS_OF_PAGE
                : mTotalItem / MAX_NUM_ITEMS_OF_PAGE + 1;
        return numOfPage;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addData(ArrayList<AppModel> apps) {
        mApps = apps;
    }

    private ArrayList<AppModel> getCurrentPageData(ArrayList<AppModel> totalApps, int page) {
        ArrayList<AppModel> subAl = null;
        if (mTotalItem > 0 && page < numOfPage-1) {
            subAl = new ArrayList<AppModel>(totalApps.subList((6 * page), (6 * page + 6)));
            d(TAG, "getCurrentPageData subAL==" + subAl.toString());
        }else if (page==numOfPage-1){
            subAl = new ArrayList<AppModel>(totalApps.subList((6 * page), mTotalItem));
        }
        return subAl;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        d(TAG, "getView position = "+position);
//        ViewHolder holder;
        View layout;
        AppsGridView gridView = null;
        if (convertView == null) {
//            holder = new ViewHolder();
            if (mTotalItem <= 0) {
                return null;
            }

            layout = mInflater.inflate(R.layout.page_layout,null);
            //gridView = new AppsGridView(parent.getContext(), getCurrentPageData(mApps, position));
//            holder.mAppsGrid = (AppsGridView)layout.findViewById(R.id.apps_grid);
            gridView = (AppsGridView)layout.findViewById(R.id.apps_grid);
            gridView.setAppsGridData(getCurrentPageData(mApps, position));
        } else {
            layout = convertView;
            LinearLayout mInfoLayout = (LinearLayout)layout;
            gridView = (AppsGridView)mInfoLayout.getChildAt(0);
            gridView.setAppsGridData(getCurrentPageData(mApps, position));
        }
        return layout;
    }
/*    private class ViewHolder{
        private AppsGridView mAppsGrid;
    }*/
}
