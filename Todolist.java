import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "data/tasks.txt";

    public void addTask(String description) {
        int id = tasks.size() + 1;
        Task newTask = new Task(id, description);
        tasks.add(newTask);
    }

    public void removeTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public void markTaskAsCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markAsCompleted();
                break;
            }
        }
    }

    public void displayTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void saveTasks() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.getId() + "," + task.getDescription() + "," + task.isCompleted());
                writer.newLine();
            }
        }
    }

    public void loadTasks() throws IOException {
        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Task task = new Task(Integer.parseInt(parts[0]), parts[1]);
                if (Boolean.parseBoolean(parts[2])) {
                    task.markAsCompleted();
                }
                tasks.add(task);
            }
        }
    }
}
