package ideas.weicong.com.notifymeright;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ideas.weicong.com.notifymeright.adapters.TasksAdapter;
import ideas.weicong.com.notifymeright.model.DayTasks;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by wei cong on 9/1/2015.
 */
@EFragment(R.layout.fragment_singledaytask)
public class TaskListFragment extends Fragment {
    @ViewById
    public ListView lvTimeWithTasks;
    @Bean
    RESTAdapter adapter;
    ArrayList<DayTasks> tasksList = new ArrayList<DayTasks>();
    public TaskListFragment() {
        super();


    }

    @AfterViews
    public void afterInject(){
        adapter.getService().getTasks(new Callback<ArrayList<DayTasks>>() {
            @Override
            public void success(ArrayList<DayTasks> dayTaskses, Response response) {
                TaskListFragment.this.tasksList = dayTaskses;
                lvTimeWithTasks.setAdapter(new TasksAdapter(getActivity(), dayTaskses));
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.getMessage());
            }
            /*
            @Override
            public void success(ArrayList<Task> tasks, Response response) {
                TaskListFragment.this.tasksList = tasks;
                lvTimeWithTasks.setAdapter(new TasksAdapter(getActivity(), tasksList));
            }

            @Override
            public void failure(RetrofitError error) {
                Logger.getLogger(TaskListFragment.class.getName()).log(Level.SEVERE, error.getMessage());
            }*/
        });
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


