// LibraryManager.java
import java.io.*;
import java.util.*;

public class LibraryManager {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private final String BOOKS_FILE = "books.txt";
    private final String MEMBERS_FILE = "members.txt";
    private Scanner sc = new Scanner(System.in);
    private int nextBookId = 100;   // starting ids (simple generator)
    private int nextMemberId = 200;

    public LibraryManager() {
        loadFromFile();
        // adjust next IDs to avoid clashes
        if (!books.isEmpty()) nextBookId = Collections.max(books.keySet()) + 1;
        if (!members.isEmpty()) nextMemberId = Collections.max(members.keySet()) + 1;
    }

    // ---------- File I/O ----------
    private void loadFromFile() {
        // load books
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Book b = Book.fromCSV(line);
                books.put(b.getBookId(), b);
            }
        } catch (FileNotFoundException e) {
            // file not present — ok, will create on save
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }

        // load members
        try (BufferedReader br = new BufferedReader(new FileReader(MEMBERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Member m = Member.fromCSV(line);
                members.put(m.getMemberId(), m);
            }
        } catch (FileNotFoundException e) {
            // ok
        } catch (IOException e) {
            System.out.println("Error reading members file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        // save books
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books.values()) {
                bw.write(b.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing books file: " + e.getMessage());
        }

        // save members
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member m : members.values()) {
                bw.write(m.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing members file: " + e.getMessage());
        }
    }

    // ---------- Operations ----------
    private void addBook() {
        sc.nextLine(); // consume newline
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = sc.nextLine().trim();
        System.out.print("Enter Category: ");
        String category = sc.nextLine().trim();

        Book b = new Book(nextBookId++, title, author, category, false);
        books.put(b.getBookId(), b);
        saveToFile();
        System.out.println("Book added with ID: " + b.getBookId());
    }

    private void addMember() {
        sc.nextLine();
        System.out.print("Enter Member Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        Member m = new Member(nextMemberId++, name, email);
        members.put(m.getMemberId(), m);
        saveToFile();
        System.out.println("Member added with ID: " + m.getMemberId());
    }

    private void issueBook() {
        System.out.print("Enter Member ID: ");
        int mid = sc.nextInt();
        System.out.print("Enter Book ID: ");
        int bid = sc.nextInt();

        Member m = members.get(mid);
        Book b = books.get(bid);
        if (m == null) { System.out.println("Member not found."); return; }
        if (b == null) { System.out.println("Book not found."); return; }
        if (b.isIssued()) { System.out.println("Book already issued."); return; }

        b.markAsIssued();
        m.addIssuedBook(bid);
        saveToFile();
        System.out.println("Book " + bid + " issued to member " + mid);
    }

    private void returnBook() {
        System.out.print("Enter Member ID: ");
        int mid = sc.nextInt();
        System.out.print("Enter Book ID: ");
        int bid = sc.nextInt();

        Member m = members.get(mid);
        Book b = books.get(bid);
        if (m == null) { System.out.println("Member not found."); return; }
        if (b == null) { System.out.println("Book not found."); return; }
        if (!b.isIssued()) { System.out.println("Book is not issued."); return; }

        b.markAsReturned();
        m.returnIssuedBook(bid);
        saveToFile();
        System.out.println("Book " + bid + " returned by member " + mid);
    }

    private void searchBooks() {
        sc.nextLine();
        System.out.print("Search by (1) Title (2) Author (3) Category: ");
        int ch = Integer.parseInt(sc.nextLine());
        System.out.print("Enter search text: ");
        String q = sc.nextLine().toLowerCase();

        List<Book> results = new ArrayList<>();
        for (Book b : books.values()) {
            if (ch == 1 && b.getTitle().toLowerCase().contains(q)) results.add(b);
            if (ch == 2 && b.getAuthor().toLowerCase().contains(q)) results.add(b);
            if (ch == 3 && b.getCategory().toLowerCase().contains(q)) results.add(b);
        }

        if (results.isEmpty()) System.out.println("No results found.");
        else {
            System.out.println("Search results:");
            for (Book b : results) b.displayBookDetails();
        }
    }

    private void sortBooks() {
        System.out.print("Sort by (1) Title (2) Author: ");
        int ch = sc.nextInt();
        List<Book> list = new ArrayList<>(books.values());
        if (ch == 1) Collections.sort(list); // Comparable - title
        else Collections.sort(list, Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER));
        for (Book b : list) b.displayBookDetails();
    }

    // ---------- Menu ----------
    public void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== City Library Digital Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. List All Books");
            System.out.println("8. List All Members");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try { choice = sc.nextInt(); }
            catch (InputMismatchException e) { System.out.println("Invalid choice."); sc.nextLine(); continue; }

            switch (choice) {
                case 1: addBook(); break;
                case 2: addMember(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: searchBooks(); break;
                case 6: sortBooks(); break;
                case 7:
                    for (Book b : books.values()) b.displayBookDetails();
                    break;
                case 8:
                    for (Member m : members.values()) m.displayMemberDetails();
                    break;
                case 9:
                    System.out.println("Saving data and exiting...");
                    saveToFile();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        lm.mainMenu();
    }
}

