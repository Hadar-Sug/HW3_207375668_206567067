import java.util.ArrayList;

public class ToDoList implements Cloneable {
    private ArrayList<Task> tasks = new ArrayList<>();// need to initialize?


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task taskToAdd) {
        for (Task task : tasks) {
            if (taskToAdd == task)
                throw new TaskAlreadyExistsException("The task already exists");
        }
        tasks.add(taskToAdd);
    }

    @Override
    public ToDoList clone() { //public?
        try{
            ToDoList temp = (ToDoList) super.clone(); //shallow copy of our list
            for (int i = 0; i < temp.tasks.size(); i++) {
                Task tempTask = new Task(temp.tasks.get(i)); // deep copy of each task
                temp.tasks.set(i, tempTask); //set the deep copy we made
            }
            return temp;
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

    public ToDoList createUncompletedList() {
        ToDoList uncompleted = new ToDoList();
        for (Task task : this.tasks) {
            if (!task.getTaskFinished()) {
                uncompleted.addTask(task);
            }
        }
        ToDoList uncompletedCopy = new ToDoList();
        uncompletedCopy = uncompleted.clone();
        return uncompletedCopy;


    }
}
