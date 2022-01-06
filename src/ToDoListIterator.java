import java.util.Iterator;

public class ToDoListIterator implements Iterator<Task> {

    private ToDoList toDoList;
    private int index = 0;
    private static ScanningType scanningType = ScanningType.ALL;

    public static void setScanningType(ScanningType scanningType) {
        ToDoListIterator.scanningType = scanningType;
    }

    public ToDoListIterator(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public boolean hasNext() {
        switch (scanningType){
            case ALL: return index < toDoList.getTasks().size();
            case COMPLETED: {
                while (index < toDoList.getTasks().size() && !toDoList.getTask(index).isCompleted()){
                    index++;// want to iterate till we find a completed task if we find one before the end return true
                    if (toDoList.getTask(index).isCompleted()){
                        return true;
                    }
                }
                return false;

            }
            case UNCOMPLETED:
                while (index < toDoList.getTasks().size() && toDoList.getTask(index).isCompleted()){
                    index++;// want to iterate till we find an uncompleted task if we find one before the end return true
                    if (!toDoList.getTask(index).isCompleted()){
                        return true;
                    }
                }
                return false;
        }
        return false; //shouldn't reach here

    }

    @Override
    public Task next() {
        return toDoList.getTask(index);//index is always on the next viable option
    }

}
