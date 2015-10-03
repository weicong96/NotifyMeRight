package ideas.weicong.com.notifymeright;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.Calendar;
import java.util.logging.Logger;

import ideas.weicong.com.notifymeright.model.Task;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by wei cong on 9/5/2015.
 */
@EFragment(R.layout.fragment_addtask)
public class AddTaskFragment extends Fragment {
    @Bean
    RESTAdapter adapter;

    @ViewById
    public EditText etTitle;

    @ViewById
    public DatePicker cvDate;

    @ViewById
    public TimePicker tpTime;
    @ViewById
    public Button btnSubmit;
    @ViewById
    public Spinner spPattern;

    @AfterViews
    public void afterViews(){
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Task task = new Task();
                task.setName(etTitle.getText().toString());
                task.setLat(1.3);
                task.setLng(103.22);

                Calendar c = Calendar.getInstance();
                c.set(cvDate.getYear(), cvDate.getMonth(), cvDate.getDayOfMonth(), tpTime.getCurrentHour(), tpTime.getCurrentMinute());
                task.setTime(c.getTime().toGMTString());

                adapter.getService().createTask(task, new Callback<Task>() {
                    @Override
                    public void success(Task task, Response response) {
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, new TaskListFragment_()).addToBackStack(null).commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        throw new Error(error.getMessage());
                    }
                });
            }
        });
    }
}
