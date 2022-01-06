import java.sql.Date;

public class Task implements Cloneable {
    private String taskName;
    private Date dueDate; // make sure format is DD.MM.YYYY. do this when printingx
    private Boolean taskFinished = false;

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

    public Boolean getTaskFinished() {
        return taskFinished;
    }

    public void setTaskFinished(Boolean taskFinished) {
        this.taskFinished = taskFinished;
    }
}
