package nick.example.simplelauncher;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author: Yefenglin on 2016/6/28  18:23
 * E-mail: ye_fenglin@163.com
 */
public class AppListAdapter extends ArrayAdapter<AppModel> {
    private final LayoutInflater mInflater;
    private int[] bg_image=new int[]{R.drawable.blue,R.drawable.green,R.drawable.orange,R.drawable.pink,
            R.drawable.purple,R.drawable.yellow};
    public AppListAdapter (Context context) {
        super(context, android.R.layout.simple_list_item_2);

        mInflater = LayoutInflater.from(context);
    }


    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addAll(Collection<? extends AppModel> items) {
        //If the platform supports it, use addAll, otherwise add in loop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            super.addAll(items);
        }else{
            for(AppModel item: items){
                super.add(item);
            }
        }
    }

    /**
     * Populate new items in the list.
     */
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        AppModel item = getItem(position);
        if (convertView == null) {
            view = mInflater.inflate(R.layout.list_item_icon_text, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.icon =(ImageView)view.findViewById(R.id.icon);
            viewHolder.label = (TextView)view.findViewById(R.id.text);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        view.setBackgroundResource(bg_image[position%(bg_image.length)]);
        viewHolder.icon.setImageDrawable(item.getIcon());
        viewHolder.label.setText(item.getLabel());
        return view;
    }

    private class ViewHolder {
        ImageView icon;
        TextView label;
    }
}
