import java.util.Scanner;

public class Student {
    private int rollNo;
    private String name;
    private String course;
    private double marks;
    private char grade;

    // Default constructor
    public Student() {
        this.rollNo = 0;
        this.name = "";
        this.course = "";
        this.marks = 0.0;
        this.grade = 'D';
    }

    // Parameterized constructor
    public Student(int rollNo, String name, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.marks = marks;
        this.calculateGrade();
    }

    // Input details from user (uses provided scanner)
    public void inputDetails(Scanner sc) {
        System.out.print("Enter Roll No: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid. Enter a valid integer for Roll No: ");
            sc.next();
        }
        this.rollNo = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Name: ");
        this.name = sc.nextLine().trim();

        System.out.print("Enter Course: ");
        this.course = sc.nextLine().trim();

        System.out.print("Enter Marks (0 - 100): ");
        while (true) {
            if (!sc.hasNextDouble()) {
                System.out.print("Invalid. Enter numeric marks (0 - 100): ");
                sc.next();
                continue;
            }
            double m = sc.nextDouble();
            if (m < 0 || m > 100) {
                System.out.print("Marks must be between 0 and 100. Enter again: ");
                continue;
            }
            this.marks = m;
            break;
        }
        sc.nextLine(); // consume newline
        calculateGrade();
    }

    // Display student details
    public void displayDetails() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name   : " + name);
        System.out.println("Course : " + course);
        System.out.println("Marks  : " + marks);
        System.out.println("Grade  : " + grade);
        System.out.println("-------------------------");
    }

    // Calculate grade based on marks
    public void calculateGrade() {
        if (marks >= 90 && marks <= 100) {
            grade = 'A';
        } else if (marks >= 75) {
            grade = 'B';
        } else if (marks >= 50) {
            grade = 'C';
        } else {
            grade = 'D';
        }
    }

    // Getters (optional, used if needed)
    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }
    public char getGrade() { return grade; }
}
