package ideas.weicong.com.notifymeright.model;

/**
 * Created by wei cong on 10/2/2015.
 */
public class DayTasks {
    private String date;
    private String relativeDate;
    private Task[] items;

    public String getRelativeDate() {
        return relativeDate;
    }

    public void setRelativeDate(String relativeDate) {
        this.relativeDate = relativeDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Task[] getItems() {
        return items;
    }

    public void setItems(Task[] items) {
        this.items = items;
    }


}
