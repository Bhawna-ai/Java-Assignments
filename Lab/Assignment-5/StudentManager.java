import java.util.*;

public class StudentManager implements RecordActions {

    Map<Integer, Student> map = new HashMap<>();

    @Override
    public void addStudent(Student s) {
        map.put(s.getRollNo(), s);
    }

    @Override
    public void deleteStudent(String name) {
        map.values().removeIf(s -> s.getName().equalsIgnoreCase(name));
    }

    @Override
    public void updateStudent(String name, double marks) {
        for (Student s : map.values()) {
            if (s.getName().equalsIgnoreCase(name)) {
                try {
                    if (marks < 0 || marks > 100)
                        throw new IllegalArgumentException("Invalid marks!");

                    s.calculateGrade();
                    return;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public Student searchStudent(String name) {
        return map.values().stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    @Override
    public void viewAllStudents() {
        for (Student s : map.values()) s.displayInfo();
    }
}

