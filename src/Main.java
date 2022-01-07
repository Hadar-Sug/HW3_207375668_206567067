import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ToDoList l1 = new ToDoList();
        for (Task t : l1) {
            System.out.println("You should not reach here!");
        }

        Task t1 = new Task("SoftwareEngineering HW0", new Date(2021 - 1900, Calendar.NOVEMBER, 24));
        Task t2 = new Task("SoftwareEngineering HW1", new Date(2021 - 1900, Calendar.DECEMBER, 19));
        Task t3 = new Task("SoftwareEngineering HW2", new Date(2022 - 1900, Calendar.JANUARY, 4));
        Task t4 = new Task("SoftwareEngineering HW3", new Date(2022 - 1900, Calendar.JANUARY, 16));

        l1.addTask(t1);
        l1.addTask(t2);
        l1.addTask(t3);
        l1.addTask(t4);

        System.out.println("l1: " + l1);
        t1.setAsComplete();
        System.out.println("l1: " + l1);

        ToDoList l2 = l1.clone();
        ToDoList l3 = l1.createUncompletedList();
        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);
        System.out.println("l3: " + l3);

        System.out.println("Is l1 == l2? " + (l1 == l2));
        System.out.println("Is l1 == l3? " + (l1 == l3));
        System.out.println("Is l2 == l3? " + (l2 == l3));

        System.out.println("Are lists l1 and l2 equal? " + l1.equals(l2));
        System.out.println("Are lists l2 and l1 equal? " + l2.equals(l1));
        System.out.println("Are lists l1 and l3 equal? " + l1.equals(l3));
        System.out.println("Are lists l3 and l1 equal? " + l3.equals(l1));
        System.out.println("Are lists l2 and l3 equal? " + l2.equals(l3));
        System.out.println("Are lists l3 and l2 equal? " + l3.equals(l2));
        System.out.println("Is l1.equals(null)? " + l1.equals(null));

        t2.setAsComplete();

        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);
        System.out.println("l3: " + l3);


        System.out.println("Are lists l1 and l2 equal? " + l1.equals(l2));
        System.out.println("Are lists l2 and l1 equal? " + l2.equals(l1));
        System.out.println("Are lists l1 and l3 equal? " + l1.equals(l3));
        System.out.println("Are lists l3 and l1 equal? " + l3.equals(l1));
        System.out.println("Are lists l2 and l3 equal? " + l2.equals(l3));
        System.out.println("Are lists l3 and l2 equal? " + l3.equals(l2));


        try {
            l1.addTask(new Task("SoftwareEngineering HW0", new Date(2021 - 1900, Calendar.NOVEMBER, 24)));
        } catch (TaskAlreadyExistsException e) {
            System.out.println("Cannot add the task!");
        }
        try {
            l1.addTask(new Task("SoftwareEngineering HW0", new Date(2018, Calendar.MARCH, 21)));
        } catch (TaskAlreadyExistsException e) {
            System.out.println("Cannot add the task!");
        }
        Task clonedT1 = t1.clone();
        try {
            l1.addTask(clonedT1);
        } catch (TaskAlreadyExistsException e) {
            System.out.println("Cannot add the task!");
        }

        checkAllScans(l1);

        ToDoList l4 = new ToDoList();
        Task t5 = new Task("SoftwareEngineering HW3", new Date(2022 - 1900, Calendar.JANUARY, 16));
        System.out.println("l1: " + l1);
        System.out.println("l4: " + l4);
        System.out.println("Are lists l1 and l4 equal? " + l1.equals(l4));
        System.out.println("Are lists l4 and l1 equal? " + l4.equals(l1));

        t3.setAsComplete();
        System.out.println("l1: " + l1);
        System.out.println("l4: " + l4);
        System.out.println("Are lists l1 and l4 equal? " + l1.equals(l4));
        System.out.println("Are lists l4 and l1 equal? " + l4.equals(l1));

        l4.addTask(t5);
        System.out.println("l1: " + l1);
        System.out.println("l4: " + l4);
        System.out.println("Are lists l1 and l4 equal? " + l1.equals(l4));
        System.out.println("Are lists l4 and l1 equal? " + l4.equals(l1));

        ToDoList l5 = new ToDoList();
        Task t6 = new Task("SoftwareEngineering HW3", new Date(2022 - 1900, Calendar.SEPTEMBER, 17));
        l5.addTask(t6);
        System.out.println("l1: " + l1);
        System.out.println("l5: " + l5);
        System.out.println("Are lists l1 and l5 equal? " + l1.equals(l5));
        System.out.println("Are lists l5 and l1 equal? " + l5.equals(l1));


        checkAllScans(l5);

        t6.setAsComplete();
        l5.setScanningType(ScanningType.ALL);
        checkAllScans(l5);

        ToDoList l6 = new ToDoList();
        l6.addTask(t4);
        l6.addTask(t2);
        l6.addTask(t1);
        l6.addTask(t3);
        System.out.println("l6: " + l6);
        System.out.println("Are lists l1 and l6 equal? " + l1.equals(l6));
        System.out.println("Are lists l6 and l1 equal? " + l6.equals(l1));
    }

    /**
     * Checks all of the scan types for a given ToDoList.
     */
    private static void checkAllScans(ToDoList tdl) {
        for (Task t : tdl) {
            System.out.println(t);
            System.out.println(t.isCompleted());
            System.out.println("--------------------------");
        }

        tdl.setScanningType(ScanningType.COMPLETED);
        for (Task t : tdl) {
            System.out.println(t);
            System.out.println(t.isCompleted());
            System.out.println("--------------------------");
        }

        tdl.setScanningType(ScanningType.UNCOMPLETED);
        for (Task t : tdl) {
            System.out.println(t);
            System.out.println(t.isCompleted());
            System.out.println("--------------------------");
        }
    }
}
