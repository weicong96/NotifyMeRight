package ideas.weicong.com.notifymeright.adapters;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ideas.weicong.com.notifymeright.R;
import ideas.weicong.com.notifymeright.model.SingleEntryData;

/**
 * Created by wei cong on 9/2/2015.
 */
public class TimeTableListViewAdapter extends BaseAdapter {
    List<SingleEntryData> dataList = new ArrayList<SingleEntryData>();
    Context context;
    public TimeTableListViewAdapter(Context context, List<SingleEntryData> dataList){
        this.dataList = dataList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_timetable_item, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.tvTime_timetable = (TextView) convertView.findViewById(R.id.tvTime_timetable);
            viewHolder.tvTasks_timetable = (TextView) convertView.findViewById(R.id.tvTasks_timetable);
            viewHolder.tvTasks_timetable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TextView tv = (TextView) view;

                    //view.setBackgroundResource(R.color.timetabletask_click_filter);

                }
            });

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tvTime_timetable.setText(dataList.get(i).getStartDate() + "-" + dataList.get(i).getEndDate());
        viewHolder.tvTasks_timetable.setText("");
        return convertView;
    }

    static class ViewHolder{
        TextView tvTime_timetable;
        TextView tvTasks_timetable;
    }
}
