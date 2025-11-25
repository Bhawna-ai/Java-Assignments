// Book.java
public class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(int bookId, String title, String author, String category, boolean isIssued) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = isIssued;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isIssued() { return isIssued; }

    public void markAsIssued() { isIssued = true; }
    public void markAsReturned() { isIssued = false; }

    public void displayBookDetails() {
        System.out.printf("ID:%d | %s | %s | %s | Issued:%s%n",
                bookId, title, author, category, isIssued ? "Yes" : "No");
    }

    // CSV line for file: id,title,author,category,isIssued
    public String toCSV() {
        return String.format("%d,%s,%s,%s,%b", bookId, escape(title), escape(author), escape(category), isIssued);
    }

    public static Book fromCSV(String line) {
        String[] parts = splitCSV(line,5);
        int id = Integer.parseInt(parts[0]);
        String t = unescape(parts[1]);
        String a = unescape(parts[2]);
        String c = unescape(parts[3]);
        boolean issued = Boolean.parseBoolean(parts[4]);
        return new Book(id,t,a,c,issued);
    }

    // Comparable by title
    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    // Helpers for simple CSV escaping (commas replaced)
    private static String escape(String s) { return s.replace(",", "&#44;"); }
    private static String unescape(String s) { return s.replace("&#44;", ","); }

    private static String[] splitCSV(String line, int expected) {
        String[] parts = line.split(",", expected);
        // already escaped commas prevented simple splits for fields that contained commas.
        return parts;
    }
}

