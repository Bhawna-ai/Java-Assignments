public class Student {
    private Integer rollNo;
    private String name;
    private String email;
    private String course;
    private Double marks;
    private String grade;

    public Student(Integer rollNo, String name, String email, String course, Double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
        this.grade = computeGrade();
    }

    private String computeGrade() {
        if (marks >= 85) return "A";
        if (marks >= 70) return "B";
        if (marks >= 50) return "C";
        return "D";
    }

    public void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
    }
}

