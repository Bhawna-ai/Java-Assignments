package model;

/**
 * Student extends Person and adds student-specific fields.
 * Demonstrates method overriding and method overloading.
 */
public class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private String grade;
    private String researchArea; // optional field to show polymorphism/extra data

    // Primary constructor
    public Student(int rollNo, String name, String email, String course, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        this.grade = computeGrade(marks);
    }

    // Overloaded constructor (demonstrates method overloading)
    public Student(int rollNo, String name, String email, String course, double marks, String researchArea) {
        this(rollNo, name, email, course, marks);
        this.researchArea = researchArea;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.grade = computeGrade(marks);
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    private String computeGrade(double marks) {
        if (marks >= 85) return "A";
        if (marks >= 70) return "B";
        if (marks >= 50) return "C";
        return "D";
    }

    // Overriding abstract method from Person
    @Override
    public void displayInfo() {
        System.out.println("Student Info:");
        System.out.println("Roll No: " + rollNo);
        System.out.println(basicInfo());
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        if (researchArea != null) {
            System.out.println("Research Area: " + researchArea);
        }
        System.out.println();
    }

    // Overloaded displayInfo: brief version (demonstrates method overloading)
    public void displayInfo(boolean brief) {
        System.out.println("Student Info (brief):");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        if (!brief) {
            System.out.println("Course: " + course);
            System.out.println("Marks: " + marks);
            System.out.println("Grade: " + grade);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + ", Name: " + name + ", Email: " + email + ", Course: " + course + ", Marks: " + marks + ", Grade: " + grade;
    }
}

