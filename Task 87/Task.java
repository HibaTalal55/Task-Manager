public class Task {
    // var title/priority/done
    String title;
    String priority;
    boolean done;
    String DeadlineDate;

    // coun
    public Task(String title, String priority, String DeadlineDate) {
        this.title = title;
        this.priority = priority;
        this.done = false;
        this.DeadlineDate = DeadlineDate;
    }

    // setters and getters for all var
    // set for tittle
    public void setTitle(String title) {
        this.title = title;
    }

    // get for the title
    public String getTitle() {
        return title;
    }

    // set for priority
    public void setPriority(String priority) {
        this.priority = priority;
    }

    // get for priority
    public String getpriority() {
        return priority;
    }

    // set for done
    public void setDone(boolean done) {
        this.done = done;
    }

    // get for done
    public boolean isDone() {
        return done;
    }

    // set for date
    public void setDeadline(String DeadlineDate) {
        this.DeadlineDate = DeadlineDate;
    }

    // get for date
    public String getDeadLineDate() {
        return DeadlineDate;
    }
}
