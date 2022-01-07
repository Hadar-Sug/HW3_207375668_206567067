import java.util.Iterator;

public class ToDoListIterator implements Iterator<Task> {

    private final ToDoList toDoList;
    private int index = 0;
    private static ScanningType scanningType = ScanningType.ALL; //is static, can only have one type at any given moment

    /**
     * setter for scanning type
     * @param scanningType wanted scanning type
     */
    public static void setScanningType(ScanningType scanningType) {
        ToDoListIterator.scanningType = scanningType;
    }

    /**
     * constructor for iterator
     * @param toDoList the list were going to iterate over
     */
    public ToDoListIterator(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    /**
     * depending on scanningType, will check if hasNext
     * @return true if hasNext, false if not
     */
    @Override
    public boolean hasNext() {
        switch (scanningType) {
            case ALL:
                return index < toDoList.getTasks().size(); //just gotta check if we haven't reached the end
            case COMPLETED: {
                while (index < toDoList.getTasks().size()) { // go till we reach the end or find a completed
                    if (toDoList.getTask(index).isCompleted())
                        return true;
                    else
                        index++;
                }
                return false;
            }
            case UNCOMPLETED: {
                while (index < toDoList.getTasks().size()) { // go till we reach the end or find an uncompleted
                    if (!toDoList.getTask(index).isCompleted())
                        return true;
                    else
                        index++;
                }
                return false;
            }
        }
        return false; //shouldn't reach here
    }

    /**
     * index should always be on the next viable option
     * @return Task of the index were on
     */
    @Override
    public Task next() {
        assert index<toDoList.getTasks().size();
        return toDoList.getTask(index++);
    }

}
