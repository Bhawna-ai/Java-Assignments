package service;

import model.Student;
import java.util.*;

/**
 * StudentManager implements RecordActions and uses a HashMap for management.
 * Demonstrates prevention of duplicate roll numbers and overriding/implementation.
 */
public class StudentManager implements RecordActions {

    // Using HashMap for efficient lookup and prevention of duplicates
    private final Map<Integer, Student> studentMap = new HashMap<>();

    @Override
    public boolean addStudent(Student student) {
        int roll = student.getRollNo();
        if (studentMap.containsKey(roll)) {
            // Duplicate roll number – do not add
            System.out.println("Add failed: Student with rollNo " + roll + " already exists.");
            return false;
        }
        studentMap.put(roll, student);
        System.out.println("Student added: rollNo " + roll);
        return true;
    }

    @Override
    public boolean deleteStudent(int rollNo) {
        if (studentMap.containsKey(rollNo)) {
            studentMap.remove(rollNo);
            System.out.println("Student deleted: rollNo " + rollNo);
            return true;
        }
        System.out.println("Delete failed: No student with rollNo " + rollNo);
        return false;
    }

    @Override
    public boolean updateStudent(int rollNo, Student updatedStudent) {
        if (!studentMap.containsKey(rollNo)) {
            System.out.println("Update failed: No student with rollNo " + rollNo);
            return false;
        }
        // For safety ensure rollNo matches key
        if (updatedStudent.getRollNo() != rollNo) {
            System.out.println("Update failed: rollNo in updated object does not match key.");
            return false;
        }
        studentMap.put(rollNo, updatedStudent);
        System.out.println("Student updated: rollNo " + rollNo);
        return true;
    }

    @Override
    public Student searchStudent(int rollNo) {
        return studentMap.get(rollNo); // returns null if not found
    }

    @Override
    public List<Student> viewAllStudents() {
        List<Student> list = new ArrayList<>(studentMap.values());
        // Sort by rollNo for stable output
        list.sort(Comparator.comparingInt(Student::getRollNo));
        return list;
    }
}

