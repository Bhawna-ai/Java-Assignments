// Student.java
public class Student {
    private int rollNumber;
    private String studentName;
    private int[] marks; // marks for 3 subjects

    public Student(int rollNumber, String studentName, int[] marks) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.marks = marks;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public int[] getMarks() {
        return marks;
    }

    /**
     * Validates each subject's marks to be in range 0–100.
     * Throws InvalidMarksException when a mark is invalid.
     */
    public void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            int m = marks[i];
            if (m < 0 || m > 100) {
                throw new InvalidMarksException(
                    "Invalid marks for subject " + (i + 1) + ": " + m + ". Marks must be between 0 and 100."
                );
            }
        }
    }

    /**
     * Calculates average marks (double).
     */
    public double calculateAverage() {
        int sum = 0;
        for (int m : marks) sum += m;
        return (double) sum / marks.length;
    }

    /**
     * Displays roll number, name, marks, average and result status (Pass/Fail).
     * Passing criteria used here: student must have minimum 40 in each subject (common threshold).
     * You can change this rule if your teacher specified a different passing rule.
     */
    public void displayResult() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);
        System.out.print("Marks: ");
        for (int m : marks) {
            System.out.print(m + " ");
        }
        System.out.println();

        double avg = calculateAverage();
        System.out.println("Average: " + avg);

        boolean passed = true;
        for (int m : marks) {
            if (m < 40) { // pass threshold: 40 per subject
                passed = false;
                break;
            }
        }
        System.out.println("Result: " + (passed ? "Pass" : "Fail"));
    }
}

