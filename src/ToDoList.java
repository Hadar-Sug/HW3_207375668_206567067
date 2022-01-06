import java.util.*;
import java.util.function.Consumer;

public class ToDoList implements Cloneable, TaskIterable {
    private ArrayList<Task> tasks = new ArrayList<>();// need to initialize?


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task taskToAdd) {
        for (Task task : tasks) {
            if (task.equals(taskToAdd))
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

    /**
     * creates a deep copy of a TDL that consists only of its uncompleted tasks
     * @return copy of uncompleted task, as a ToDoList
     */
    public ToDoList createUncompletedList() {
        ToDoList uncompletedCopy = this.getUncompletedList();
        return uncompletedCopy.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != ToDoList.class)
            return false;
        ToDoList other = (ToDoList) obj;
        ToDoList uncompletedThis = this.getUncompletedList(); // only want to compare uncompleted tasks
        ToDoList uncompletedOther = other.getUncompletedList();
        Set<Task> thisListSet = new HashSet<>(uncompletedThis.tasks);// make each one as a set
        Set<Task> otherListSet = new HashSet<>(uncompletedOther.tasks);
        return thisListSet.equals(otherListSet); // compare sets, this wat we ignore order
    }

    /**
     * creates a version of a TDL with only uncompleted it in
     * @return uncompleted TDL
     */
    public ToDoList getUncompletedList(){
        ToDoList uncompleted = new ToDoList();
        setScanningType(ScanningType.UNCOMPLETED);
        Iterator<Task> it = iterator();
        while (it.hasNext()){
            uncompleted.addTask(it.next());
        }
        return uncompleted;
        //underneath is the same thing but they asked to do it this way?
   /*     for (Task task : this.tasks) {
            if (!task.isCompleted()) {
                uncompleted.addTask(task);
            }
        }
        return uncompleted;*/
    }

    @Override
    public String toString() {
        setScanningType(ScanningType.UNCOMPLETED);
        Iterator<Task> it = iterator();
        StringBuilder builder = new StringBuilder("[");
        while (it.hasNext()) {
            builder.append(", ").append(it.next().toString());
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public void setScanningType(ScanningType scanningType) {
        ToDoListIterator.setScanningType(scanningType);
    }


    @Override
    public Iterator<Task> iterator() {
        return new ToDoListIterator(this);
    }


}
