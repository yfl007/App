package nick.example.simplelauncher;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import static android.util.Log.d;

/**
 * Author: Yefenglin on 2016/6/22  14:20
 * E-mail: ye_fenglin@163.com
 */
public class PageAdapter extends BaseAdapter {
    private static final String TAG = "nick--PageAdapter";
    private static final int MAX_NUM_ITEMS_OF_PAGE = 6;
    private static final int HOST_ID = 1024;
    private static final String DIGITAL_CLOCK = "com.android.alarmclock.DigitalAppWidgetProvider";

    private Context mContext;
    private ArrayList<AppModel> mApps;
    private int mTotalItem = 0;
    private int numOfPage = 0;
    private LayoutInflater mInflater;
    private AppWidgetHost  mAppWidgetHost = null ;
    AppWidgetManager mAppWidgetManager = null;
    List<AppWidgetProviderInfo> mAppWidgetInfos;
    int mAppWidgetId;
    public PageAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext =context;
        mAppWidgetHost = new AppWidgetHost(context, HOST_ID);
        mAppWidgetHost.startListening();
        mAppWidgetManager = AppWidgetManager.getInstance(context);
        mAppWidgetId = mAppWidgetHost.allocateAppWidgetId();
        mAppWidgetInfos = mAppWidgetManager.getInstalledProviders();
    }

    @Override
    public int getCount() {
        int totalPage;
        if (mApps != null) {
            mTotalItem = mApps.size();
        }else {
            return 0;
        }
        numOfPage = ((mTotalItem-2) % MAX_NUM_ITEMS_OF_PAGE == 0) ? (mTotalItem-2) / MAX_NUM_ITEMS_OF_PAGE +1
                : (mTotalItem-2) / MAX_NUM_ITEMS_OF_PAGE +2;
        totalPage = numOfPage;
        return totalPage;
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
        if (mTotalItem<=0||page<0){
            return subAl;
        }
        if (page==0){
            subAl = new ArrayList<AppModel>(totalApps.subList(0, 2));
        }else if (page < numOfPage-1) {
            subAl = new ArrayList<AppModel>(totalApps.subList((6 * (page-1)+2), (6 * (page-1) + 8)));
        }else if (page==numOfPage-1){
            subAl = new ArrayList<AppModel>(totalApps.subList((6 * (page-1)+2), mTotalItem));
        }
        d(TAG, "getCurrentPageData:page== "+page+":"+subAl.toString());
        return subAl;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        d(TAG, "getView position = "+position);
        ViewHolder holder;
        View layout;
        if (convertView == null) {
            if (mTotalItem <= 0) {
                return null;
            }
            holder = new ViewHolder();
            layout = mInflater.inflate(R.layout.page_layout,null);

            holder.mWidgetLayout = (LinearLayout)layout.findViewById(R.id.widget_linearlayout);
            addWidget(DIGITAL_CLOCK,holder.mWidgetLayout);
            holder.mAppsGrid = (AppsGridView)layout.findViewById(R.id.apps_grid);
            holder.mAppsGrid.setAppsGridData(getCurrentPageData(mApps, position));
            layout.setTag(holder);
        } else {
            layout = convertView;
            holder = (ViewHolder)layout.getTag();
            holder.mAppsGrid.setAppsGridData(getCurrentPageData(mApps, position));

        }
        if (position==0){
            holder.mWidgetLayout.setVisibility(View.VISIBLE);
        }else {
            holder.mWidgetLayout.setVisibility(View.GONE);
        }
        return layout;
    }

    private void addWidget(String className,ViewGroup parent) {
        for (AppWidgetProviderInfo info :mAppWidgetInfos){
            d(TAG, "addWidget: " + info.provider.getClassName());
            if (info.provider.getClassName().equals(className)){
                AppWidgetHostView view = mAppWidgetHost.createView(mContext, mAppWidgetId,info);
                d(TAG, "addWidget digital:"+info.minHeight+" "+info.minHeight+" "+
                        info.resizeMode+" "+info.minResizeWidth+" "+info.minResizeHeight);
                LinearLayout.LayoutParams lp =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.CENTER_HORIZONTAL;
                parent.addView(view,lp);
                break;
            }
        }

    }
    private class ViewHolder{
        private AppsGridView mAppsGrid;
        private LinearLayout mWidgetLayout;
    }
}
