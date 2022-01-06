import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Cloneable {
    private String taskName;
    private Date dueDate; // make sure format is DD.MM.YYYY. do this when printingx
    private Boolean taskFinished = false;
    private static final SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * regular builder for Task
     * @param taskName description
     * @param dueDate due date
     */
    public Task(String taskName, Date dueDate) {
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    public Task(Task otherTask){
        this.taskName = otherTask.taskName;
        this.dueDate = otherTask.dueDate;
        this.taskFinished = otherTask.taskFinished;
    }

    @Override
    public Task clone(){ //public?
       try {
           Task temp = (Task) super.clone();
           return new Task(temp);
       } catch (CloneNotSupportedException e) {
           return null;
       }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Task.class)
            return false;
        Task other = (Task) obj;
        return taskName.equals(other.taskName) && dueDate.equals(other.dueDate);
    }

    @Override
    public String toString() {
        String dueDate = format1.format(getDueDate());
        return "(" + getTaskName() + ", " + dueDate + ")";
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean isCompleted() {
        return taskFinished;
    }

    public void setAsComplete() {
        this.taskFinished = true;
    }
}
