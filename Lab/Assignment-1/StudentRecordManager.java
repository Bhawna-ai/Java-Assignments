import java.util.ArrayList;
import java.util.Scanner;

public class StudentRecordManager {
    private ArrayList<Student> students;
    private Scanner sc;

    public StudentRecordManager() {
        students = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("\n===== Student Record Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addStudent() {
        Student s = new Student();
        s.inputDetails(sc);
        students.add(s);
        System.out.println("Student added successfully.");
    }

    private void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n--- All Student Records ---");
        for (Student s : students) {
            s.displayDetails();
        }
    }

    public void run() {
        boolean running = true;
        while (running) {
            showMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    displayAll();
                    break;
                case "3":
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        StudentRecordManager app = new StudentRecordManager();
        app.run();
    }
}

