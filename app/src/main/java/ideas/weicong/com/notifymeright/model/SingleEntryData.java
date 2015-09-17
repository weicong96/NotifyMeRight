package ideas.weicong.com.notifymeright.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wei cong on 9/1/2015.
 */
public class SingleEntryData {
    private String startDate;
    private String endDate;
    private ArrayList<Task> taskList;

    public SingleEntryData(String startDate, String endDate, ArrayList<Task> list){
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskList = list;
    }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
