import java.util.*;
import java.util.function.Consumer;


public class ToDoList implements Cloneable, TaskIterable {
    private ArrayList<Task> tasks;

    /**
     * constructor for TDL
     */
    public ToDoList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * getter for actual TDL "under the hood"
     * @return list itself
     */
    protected ArrayList<Task> getTasks() { //protected?
        return tasks;
    }

    /**
     * getter for specific task in the list
     * @param i index of task we want
     * @return task in specified index
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }


    /**
     * setter for TDL
     * @param tasks new List
     */
    protected void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * adds a task if it doesn't already exist.
     * throws unchecked exception
     * @param taskToAdd task were adding
     */
    public void addTask(Task taskToAdd) {
        for (Task task : this.tasks) {
            if (task.equals(taskToAdd) || task.getTaskName().equals(taskToAdd.getTaskName()))
                throw new TaskAlreadyExistsException("The task already exists");
        }
        this.tasks.add(taskToAdd);
    }

    /**
     * copy constructor
     * @param other TDL were copying
     */
  /*  private ToDoList(ToDoList other){
        this.tasks = (ArrayList<Task>) other.tasks.clone();

    }*/

    /**
     * deep clone of a TDL
     * @return deep clone of TDL
     */
    @Override
    public ToDoList clone() { //public?
        try{
            ToDoList temp = (ToDoList) super.clone(); //shallow copy of our list
            temp.tasks = (ArrayList<Task>) this.tasks.clone();
//            ToDoList copy = new ToDoList(temp); // maybe unnecessary
            for (int i = 0; i < tasks.size(); i++) {
                temp.tasks.set(i,temp.getTask(i).clone());
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

    /**
     * checks if each TDL's uncompleted tasks are the same, regardless of the order
     * @param obj what were comparing with
     * @return true if the same' false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != ToDoList.class)
            return false;
        ToDoList other = (ToDoList) obj;
        ToDoList uncompletedThis = this.getUncompletedList(); // only want to compare uncompleted tasks
        ToDoList uncompletedOther = other.getUncompletedList();
        return uncompletedThis.containsAll(uncompletedOther) && uncompletedOther.containsAll(uncompletedThis);
    }

    /**
     * creates a version of a TDL with only uncompleted it in
     * @return uncompleted TDL
     */
    public ToDoList getUncompletedList(){
        ToDoList uncompleted = new ToDoList();
        setScanningType(ScanningType.UNCOMPLETED);
        for (Task task : this) {
            uncompleted.addTask(task);
        }
        setScanningType(ScanningType.ALL);
        return uncompleted;
    }

    /**
     * string of the TDL in requested format
     * @return [(description, dueDate), (description, dueDate), …, (description, dueDate)]
     */
    @Override
    public String toString() {
        ToDoList uncompleted = this.getUncompletedList();
        return uncompleted.tasks.toString();
    }

    /**
     * setter for scanning type
     * @param scanningType wanted type
     */
    @Override
    public void setScanningType(ScanningType scanningType) {
        ToDoListIterator.setScanningType(scanningType);
    }

    /**
     * gets and iterator for th TDL
     * @return iterator
     */
    @Override
    public Iterator<Task> iterator() {
        return new ToDoListIterator(this);
    }

    /**
     * checks if this TDl contains all of others TDl's Tasks
     * @param other TDL were checking are equal
     * @return true if
     */
    private boolean containsAll(ToDoList other){
        for (Task task:other.tasks) {
            if (!this.contains(task))
                return false;
        }
        return true;
    }

    /**
     * checks if this TDL contains a specific Task
     * @param other task were checking exists
     * @return true if exists, false if not
     */
    private boolean contains(Task other){
        for (Task task:tasks) {
            if (other.equals(task))
                return true;
        }
        return false;
    }
}
