import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        try {
            toDoList.loadTasks();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Remove Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    toDoList.addTask(description);
                    break;
                case 2:
                    toDoList.displayTasks();
                    break;
                case 3:
                    System.out.print("Enter task ID to remove: ");
                    int removeId = scanner.nextInt();
                    toDoList.removeTask(removeId);
                    break;
                case 4:
                    System.out.print("Enter task ID to mark as completed: ");
                    int completeId = scanner.nextInt();
                    toDoList.markTaskAsCompleted(completeId);
                    break;
                case 5:
                    try {
                        toDoList.saveTasks();
                        System.out.println("Tasks saved. Exiting...");
                    } catch (IOException e) {
                        System.out.println("Error saving tasks: " + e.getMessage());
                    }
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
