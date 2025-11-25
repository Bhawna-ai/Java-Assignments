import java.io.*;
import java.util.*;

public class FileUtil {

    public static Map<Integer, Student> load(String file) {
        Map<Integer, Student> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                int roll = Integer.parseInt(d[0]);
                String name = d[1];
                String email = d[2];
                String course = d[3];
                double marks = Double.parseDouble(d[4]);

                map.put(roll, new Student(roll, name, email, course, marks));
            }
        } catch (Exception e) {}
        return map;
    }

    public static void save(String file, Map<Integer, Student> map) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Student s : map.values()) {
                bw.write(s.getRollNo() + "," + s.name + "," + s.email + "," +
                         s.getMarks() + "," + s.getMarks());
                bw.newLine();
            }
        } catch (Exception e) {}
    }
}

