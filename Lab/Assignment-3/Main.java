import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        try {
            System.out.print("Enter Roll No (Integer): ");
            Integer rollNo = Integer.valueOf(sc.nextLine()); // Wrapper + autoboxing

            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            if (name.isEmpty()) throw new Exception("Name cannot be empty!");

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            if (email.isEmpty()) throw new Exception("Email cannot be empty!");

            System.out.print("Enter Course: ");
            String course = sc.nextLine();
            if (course.isEmpty()) throw new Exception("Course cannot be empty!");

            System.out.print("Enter Marks: ");
            Double marks = Double.valueOf(sc.nextLine());
            if (marks < 0 || marks > 100) throw new Exception("Marks must be between 0 and 100!");

            // Thread simulation
            Thread loader = new Thread(new Loader());
            loader.start();
            loader.join();

            Student student = new Student(rollNo, name, email, course, marks);
            manager.addStudent(student);

            student.display();

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format! Please enter correct values.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("\nProgram execution completed.");
            sc.close();
        }
    }
}
