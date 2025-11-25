package service;

import model.Student;
import java.util.List;

public interface RecordActions {
    boolean addStudent(Student student);          // return false if duplicate rollNo
    boolean deleteStudent(int rollNo);            // true if deleted
    boolean updateStudent(int rollNo, Student updatedStudent); // update fields for rollNo
    Student searchStudent(int rollNo);            // return null if not found
    List<Student> viewAllStudents();
}

