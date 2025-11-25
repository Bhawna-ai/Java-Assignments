import java.util.*;

public class StudentManager {
    List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public void viewStudents() {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) System.out.println(it.next());
    }

    public void searchByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public void deleteByName(String name) {
        students.removeIf(s -> s.getName().equalsIgnoreCase(name));
    }

    public void sortByMarks() {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
    }
}

