public class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private String grade;

    public Student(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    public void calculateGrade() {
        if (marks >= 85) grade = "A";
        else if (marks >= 70) grade = "B";
        else if (marks >= 55) grade = "C";
        else grade = "D";
    }

    @Override
    public void displayInfo() {
        System.out.println("Roll No: " + rollNo + "\nName: " + name +
                "\nEmail: " + email + "\nCourse: " + course +
                "\nMarks: " + marks + "\nGrade: " + grade + "\n");
    }
}

