import java.util.HashMap;

public class StudentManager implements RecordActions {

    private HashMap<Integer, Student> studentMap = new HashMap<>();

    @Override
    public void addStudent(Student s) {
        studentMap.put(s.rollNo, s);
    }

    @Override
    public Student searchStudent(Integer rollNo) throws StudentNotFoundException {
        if (!studentMap.containsKey(rollNo)) {
            throw new StudentNotFoundException("Student not found with Roll No: " + rollNo);
        }
        return studentMap.get(rollNo);
    }
}
