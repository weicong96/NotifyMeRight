package ideas.weicong.com.notifymeright;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ideas.weicong.com.notifymeright.adapters.TimeTableListViewAdapter;
import ideas.weicong.com.notifymeright.model.SingleEntryData;
import ideas.weicong.com.notifymeright.model.Task;

/**
 * Created by wei cong on 9/1/2015.
 */
@EFragment(R.layout.fragment_singledaytask)
public class TimeTableFragment extends Fragment {
    @ViewById
    public ListView lvTimeWithTasks;

    public double scale = 0.5;
    private int[] timings;

    List<SingleEntryData> dataList = new ArrayList<SingleEntryData>();

    public TimeTableFragment() {
        super();

        Calendar c = Calendar.getInstance();
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task = new Task();
       // task.setStartDate(0);
        //task.setEndDate(0);

        tasks.add(task);
        timings = new int[]{0, 100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,2100,2200,2300};
        for(int i = 0; i < timings.length;i++){
            //if(scale == 1) {
            String formatted = String.format("%04d",timings[i]);
            String formattedEnd = String.format("%04d",(i==(timings.length-1)) ? timings[0] : timings[i+1]);

            dataList.add(new SingleEntryData(formatted, formattedEnd, tasks));
            //}
        }
    }

    @AfterViews
    public void afterInject(){
        lvTimeWithTasks.setAdapter(new TimeTableListViewAdapter(getActivity(),dataList));
        this.setHasOptionsMenu(true);
    }
    private Menu menu;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        this.menu = menu;
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame,new AddTaskFragment_()).addToBackStack(null).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


