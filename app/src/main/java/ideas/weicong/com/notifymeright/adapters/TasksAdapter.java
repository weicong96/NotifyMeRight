package ideas.weicong.com.notifymeright.adapters;

import android.app.ActionBar;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ideas.weicong.com.notifymeright.R;
import ideas.weicong.com.notifymeright.model.DayTasks;
import ideas.weicong.com.notifymeright.model.Task;

/**
 * Created by wei cong on 9/20/2015.
 */
public class TasksAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DayTasks> tasks;
    public TasksAdapter(Context context, ArrayList<DayTasks> tasks){
        this.context = context;
        this.tasks = tasks;
    }
    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        TaskViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_timetable_item, viewGroup, false);

            viewHolder = new TaskViewHolder();
            viewHolder.tvTime_timetable = (TextView) convertView.findViewById(R.id.tvTime_timetable);
            viewHolder.llTasks = (LinearLayout) convertView.findViewById(R.id.llTasks);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (TaskViewHolder)convertView.getTag();
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewHolder.tvTime_timetable.setText(tasks.get(i).getRelativeDate());
        for(Task task :  tasks.get(i).getItems()){
            LinearLayout llTaskView = (LinearLayout)inflater.inflate(R.layout.single_task_textview, null, false);

            TextView tvTaskName = (TextView)llTaskView.findViewById(R.id.tvTaskName);

            tvTaskName.setText(task.getName());
            viewHolder.llTasks.addView(llTaskView);
        }
        return convertView;
    }
    static class TaskViewHolder{
        TextView tvTime_timetable;
        LinearLayout llTasks;
    }
}
