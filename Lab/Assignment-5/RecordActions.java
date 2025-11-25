public interface RecordActions {
    void addStudent(Student s);
    void deleteStudent(String name);
    void updateStudent(String name, double marks);
    Student searchStudent(String name);
    void viewAllStudents();
}

