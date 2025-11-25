// ResultManager.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class ResultManager {

    private Student[] students;
    private int count;
    private final int CAPACITY = 100; // can store up to 100 students
    private Scanner sc;

    public ResultManager() {
        students = new Student[CAPACITY];
        count = 0;
        sc = new Scanner(System.in);
    }

    /**
     * Adds a student after reading input and validating marks.
     * Declared to throw InvalidMarksException to show checked exception propagation.
     */
    public void addStudent() throws InvalidMarksException, InputMismatchException {
        if (count >= CAPACITY) {
            System.out.println("Student storage is full. Cannot add more students.");
            return;
        }

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        Student s = new Student(roll, name, marks);

        // validate marks; this may throw InvalidMarksException
        s.validateMarks();

        // if validation passed, add student
        students[count++] = s;
        System.out.println("Student added successfully. Returning to main menu...");
    }

    /**
     * Displays details for a specific student by roll number.
     */
    public void showStudentDetails() {
        System.out.print("Enter Roll Number to search: ");
        int roll = sc.nextInt();

        Student found = null;
        for (int i = 0; i < count; i++) {
            if (students[i].getRollNumber() == roll) {
                found = students[i];
                break;
            }
        }

        if (found != null) {
            found.displayResult();
            System.out.println("Search completed.");
        } else {
            System.out.println("Student with roll number " + roll + " not found.");
        }
    }

    /**
     * Main menu that handles choices and demonstrates exception handling.
     * It uses try-catch blocks to handle InvalidMarksException and InputMismatchException.
     */
    public void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Student Result Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Student Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        try {
                            addStudent();
                        } catch (InvalidMarksException ime) {
                            // handle custom checked exception
                            System.out.println("Error: " + ime.getMessage() + " Returning to main menu...");
                        }
                        break;
                    case 2:
                        try {
                            showStudentDetails();
                        } catch (InputMismatchException ime) {
                            // if user type mismatch when entering roll number
                            System.out.println("Invalid input. Please enter numbers where required.");
                            sc.nextLine(); // clear buffer
                        }
                        break;
                    case 3:
                        System.out.println("Exiting program. Thank you!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric choices only.");
                sc.nextLine(); // clear the invalid token
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public void closeScanner() {
        if (sc != null) sc.close();
    }

    public static void main(String[] args) {
        ResultManager rm = new ResultManager();
        try {
            rm.mainMenu();
        } finally {
            // finally ensures scanner is closed even if the program exits unexpectedly
            rm.closeScanner();
        }
    }
}

