package nick.example.simplelauncher;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Author: Yefenglin on 2016/6/28  18:23
 * E-mail: ye_fenglin@163.com
 */
public class AppsGridView extends GridView {
    private Context mContext;
    private AppListAdapter mAdapter;
    final private AdapterView.OnItemClickListener mOnClickListener
            = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            onGridItemClick((GridView) parent, v, position, id);
        }
    };

    private void onGridItemClick(GridView parent, View v, int position, long id) {
        AppModel app = getAdpater().getItem(position);
        if (app != null) {
            Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(app.getApplicationPackageName());
            if (intent != null) {
                mContext.startActivity(intent);
            }
        }
    }

    protected void setAppsGridData(ArrayList<AppModel> apps) {
        mAdapter = new AppListAdapter(mContext);
        mAdapter.addAll(apps);
        setAdapter(mAdapter);
        setNumColumns(2);
        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        setOnItemClickListener(mOnClickListener);
    }

    public AppsGridView(Context context) {
        super(context);
        mContext = context;
        setSelector(R.drawable.selected);

    }

    public AppsGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setSelector(R.drawable.selected);
    }

    public AppsGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        setSelector(R.drawable.selected);
    }

/*    public AppsGridView(Context context,AttributeSet attrs,ArrayList<AppModel> apps) {

        super(context,attrs);
        mContext = context;
//        setAppsAdapter(context, apps);
//        setNumColumns(2);
//        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//        setHorizontalSpacing(convertDpToPixels(2, context));
//        setVerticalSpacing(convertDpToPixels(2, context));
//        setOnItemClickListener(mOnClickListener);
    }*/
    private AppListAdapter getAdpater(){
        return mAdapter;
    }
    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
    }

}
