public interface RecordActions {
    void addStudent(Student s);
    Student searchStudent(Integer rollNo) throws StudentNotFoundException;
}

