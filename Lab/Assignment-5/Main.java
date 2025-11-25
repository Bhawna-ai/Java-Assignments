import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager sm = new StudentManager();
        String file = "students.txt";

        sm.map = FileUtil.load(file);

        int ch;
        do {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1 Add\n2 View\n3 Search\n4 Delete\n5 Sort\n6 Save & Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Roll No: ");
                    int r = sc.nextInt(); sc.nextLine();

                    System.out.print("Name: ");
                    String n = sc.nextLine();

                    System.out.print("Email: ");
                    String e = sc.nextLine();

                    System.out.print("Course: ");
                    String c = sc.nextLine();

                    System.out.print("Marks: ");
                    double m = sc.nextDouble();

                    Thread t = new Thread(new Loader());
                    t.start();

                    sm.addStudent(new Student(r, n, e, c, m));
                    break;

                case 2:
                    sm.viewAllStudents();
                    break;

                case 3:
                    System.out.print("Name: ");
                    Student s = sm.searchStudent(sc.nextLine());
                    if (s == null) System.out.println("Not found!");
                    else s.displayInfo();
                    break;

                case 4:
                    System.out.print("Name: ");
                    sm.deleteStudent(sc.nextLine());
                    break;

                case 5:
                    sm.map.values().stream()
                            .sorted((a, b) -> Double.compare(b.getMarks(), a.getMarks()))
                            .forEach(Student::displayInfo);
                    break;

                case 6:
                    FileUtil.save(file, sm.map);
                    System.out.println("Saved & Exiting...");
                    break;
            }
        } while (ch != 6);
    }
}

