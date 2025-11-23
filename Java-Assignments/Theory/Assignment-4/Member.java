// Member.java
import java.util.ArrayList;
import java.util.List;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private List<Integer> issuedBooks; // store book IDs

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Integer> getIssuedBooks() { return issuedBooks; }

    public void addIssuedBook(int bookId) {
        if (!issuedBooks.contains(bookId)) issuedBooks.add(bookId);
    }

    public void returnIssuedBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }

    public void displayMemberDetails() {
        System.out.printf("ID:%d | %s | %s | IssuedBooks:%s%n",
                memberId, name, email, issuedBooks.toString());
    }

    // CSV for file: id,name,email,book1;book2;...
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(memberId).append(",").append(escape(name)).append(",").append(escape(email)).append(",");
        for (int i = 0; i < issuedBooks.size(); i++) {
            if (i > 0) sb.append(";");
            sb.append(issuedBooks.get(i));
        }
        return sb.toString();
    }

    public static Member fromCSV(String line) {
        String[] parts = line.split(",",4);
        int id = Integer.parseInt(parts[0]);
        String name = unescape(parts[1]);
        String email = unescape(parts[2]);
        Member m = new Member(id,name,email);
        if (parts.length == 4 && !parts[3].isEmpty()) {
            String[] bids = parts[3].split(";");
            for (String b : bids) {
                try { m.addIssuedBook(Integer.parseInt(b)); } catch (NumberFormatException e) { /* ignore */ }
            }
        }
        return m;
    }

    private static String escape(String s) { return s.replace(",", "&#44;"); }
    private static String unescape(String s) { return s.replace("&#44;", ","); }
}

