import model.Student;
import service.StudentManager;
import service.RecordActions;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RecordActions manager = new StudentManager();

        // Create students
        Student s1 = new Student(101, "Ankit", "ankit@mail.com", "B.Tech", 78.5);
        Student s2 = new Student(102, "Riya", "riya@mail.com", "M.Tech", 92.0, "AI");
        Student s3 = new Student(101, "Duplicate", "dup@mail.com", "B.Tech", 60.0);

        // Add students
        manager.addStudent(s1); // should add
        manager.addStudent(s2); // should add
        manager.addStudent(s3); // should fail (duplicate roll)

        System.out.println("\n--- View All Students ---");
        List<Student> all = manager.viewAllStudents();
        for (Student s : all) {
            s.displayInfo(); // overridden full display
        }

        System.out.println("--- Demo overloaded display (brief) for roll 101 ---");
        Student found = manager.searchStudent(101);
        if (found != null) {
            found.displayInfo(true); // overloaded brief display
        }

        // Update student
        Student updated = new Student(101, "Ankit Updated", "ankit_new@mail.com", "B.Tech", 88.0);
        manager.updateStudent(101, updated);

        System.out.println("\n--- After Update ---");
        Student s = manager.searchStudent(101);
        if (s != null) s.displayInfo();

        // Delete
        manager.deleteStudent(102);

        System.out.println("\n--- Final list ---");
        for (Student st : manager.viewAllStudents()) {
            System.out.println(st);
        }

        // Note: finalize message may or may not appear depending on GC
    }
}
