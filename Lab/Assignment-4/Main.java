import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        String filename = "students.txt";
        manager.students = FileUtil.loadStudents(filename);

        int choice;
        do {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1. Add Student\n2. View All Students\n3. Search by Name");
            System.out.println("4. Delete by Name\n5. Sort by Marks\n6. Save and Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
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
                    manager.addStudent(new Student(r, n, e, c, m));
                    break;

                case 2:
                    manager.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter name: ");
                    manager.searchByName(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter name: ");
                    manager.deleteByName(sc.nextLine());
                    break;

                case 5:
                    manager.sortByMarks();
                    System.out.println("Sorted Student List:");
                    manager.viewStudents();
                    break;

                case 6:
                    FileUtil.saveStudents(filename, manager.students);
                    System.out.println("Saved and exiting...");
                    break;
            }
        } while (choice != 6);
    }
}
