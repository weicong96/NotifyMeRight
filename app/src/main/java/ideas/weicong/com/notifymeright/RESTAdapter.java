package ideas.weicong.com.notifymeright;

/**
 * Created by wei cong on 9/5/2015.
 */

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;


import java.util.ArrayList;

import ideas.weicong.com.notifymeright.model.DayTasks;
import ideas.weicong.com.notifymeright.model.Task;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.RestAdapter;
@EBean(scope = EBean.Scope.Singleton)
public class RESTAdapter {
    private String END_POINT = "http://192.168.1.4:8000/";

    public RESTInterface getService() {
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(END_POINT).setLogLevel(RestAdapter.LogLevel.FULL).build();

        RESTInterface service = adapter.create(RESTInterface.class);
        return service;
    }

    public interface RESTInterface {

        @POST("/task")
        public void createTask(@Body Task task, Callback<Task> cb);

        @GET("/task")
        public void getTasks(Callback<ArrayList<DayTasks>> cb);

    }
}