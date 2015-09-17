package ideas.weicong.com.notifymeright.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ideas.weicong.com.notifymeright.R;

/**
 * Created by wei cong on 9/1/2015.
 */
public class DrawerItemsListAdapter extends ArrayAdapter<String> {
    Context context;
    CharSequence[] items;
    public DrawerItemsListAdapter(Context context, String[] objects) {
        super(context, R.layout.single_drawerlist, objects);
        this.items = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        view = inflater.inflate(R.layout.single_drawerlist, parent, false);

        TextView name = (TextView) view.findViewById(R.id.drawerlist_textview);
        name.setText(items[position]);
        return view;
    }
}
