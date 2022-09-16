package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        //System.out.println("All data: ");
        printData(tasksData);

        printDataUsingStream(tasksData);

        //System.out.println("Printing deadlines");
        printDeadlines(tasksData);
        printDeadlinesUsingStreams(tasksData);

        System.out.println("Total number of deadlines counted using loop: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines counted using streams: " + countDeadlinesUsingStreams(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static long countDeadlinesUsingStreams(ArrayList<Task> tasksData){
        return tasksData.parallelStream().filter(task -> task instanceof Deadline).count();
    }


    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using loop");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStream(ArrayList<Task> tasksData){
        System.out.println("Printing data using streams");
        tasksData.stream().forEach(System.out::println);
        //Passing in a reference to println (method) to the stream to print out tasks;
    }


    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines using loop");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData){
        System.out.println("Printing deadlines using streams");
        //Potentially use all the cores available to do the computation
        tasksData.parallelStream()
                .filter((t -> t instanceof Deadline))
                .forEach(System.out::println);
    }

}