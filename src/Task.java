import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Cloneable {
    private String taskName;
    private Date dueDate; // make sure format is DD.MM.YYYY. do this when printing
    private Boolean taskFinished = false;
    private static final SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * regular constructor for Task
     * @param taskName description
     * @param dueDate due date
     */
    public Task(String taskName, Date dueDate) {
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    /**
     * deep copy constructor
     * @param otherTask task were cloning
     */
    public Task(Task otherTask){
        this.taskName = otherTask.taskName;
        this.dueDate = otherTask.dueDate;
        this.taskFinished = otherTask.taskFinished;
    }

    /**
     * cloning using our copy constructor
     * @return deep clone
     */
    @Override
    public Task clone(){ //public?
       try {
           Task temp = (Task) super.clone();
           return new Task(temp);
       } catch (CloneNotSupportedException e) {
           return null;
       }
    }

    /**
     * compares with object
     * @param obj we're comparing with
     * @return true if same name and dueDate, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Task.class)
            return false;
        Task other = (Task) obj;
        return taskName.equals(other.taskName) && dueDate.equals(other.dueDate) && this.taskFinished==other.taskFinished;
    }

    /**
     * returns Task in requested string format
     * @return (description, DueDate)
     */
    @Override
    public String toString() {
        String dueDate = format1.format(getDueDate()); // supposed to give us the date in wanted formant
        return getTaskName() + ", " + dueDate;
    }

    /**
     * getter for task name
     * @return task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * setter for task name
     * @param taskName new taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * getter for dueDate
     * @return dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * setter for dueDate
     * @param dueDate new date
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * getter for taskFinished status
     * @return status of task
     */
    public Boolean isCompleted() {
        return taskFinished;
    }

    /**
     * setter for taskFinished status
     */
    public void setAsComplete() {
        this.taskFinished = true;
    }
}
