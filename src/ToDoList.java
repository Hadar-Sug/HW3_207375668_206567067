import java.util.*;
import java.util.function.Consumer;

public class ToDoList implements Cloneable, TaskIterable {
    private ArrayList<Task> tasks = new ArrayList<>();// need to initialize?

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
        for (Task task : tasks) {
            if (task.equals(taskToAdd))
                throw new TaskAlreadyExistsException("The task already exists");
        }
        tasks.add(taskToAdd);
    }

    /**
     * deep clone of a TDL
     * @return deep clone of TDL
     */
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
        //hope this works
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
        for (Task task : this.tasks) {
            if (!task.isCompleted()) {
                uncompleted.addTask(task);
            }
        }
        return uncompleted;
        //underneath is the same thing but they asked to do it this way?
/*        setScanningType(ScanningType.UNCOMPLETED);
        Iterator<Task> it = iterator();
        while (it.hasNext()){
            uncompleted.addTask(it.next());
        }
        return uncompleted;*/
    }

    /**
     * string of the TDL in requested format
     * @return [(description, dueDate), (description, dueDate), …, (description, dueDate)]
     */
    @Override
    public String toString() {
        ToDoList uncompletedCopy = this.getUncompletedList(); // lets get only the uncompleted tasks
        StringBuilder builder = new StringBuilder("[");
        for (Task task:uncompletedCopy) // go over the uncompleted list and add each item to the builder
            builder.append(", ").append(task.toString());
        builder.append("]");
        return builder.toString();
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
}
