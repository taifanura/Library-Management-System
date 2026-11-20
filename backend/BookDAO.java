
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    
    public boolean addBook(Book book) throws SQLException {

        String sql = "INSERT INTO books " +
                "(title, author, isbn, book_code, category, edition, publisher) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getBookCode());
            ps.setString(5, book.getCategory());
            ps.setString(6, book.getEdition());
            ps.setString(7, book.getPublisher());

            return ps.executeUpdate() > 0;
        }
    }

    
    public List<Book> getAllBooks() throws SQLException {

        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books ORDER BY book_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("book_code"),
                        rs.getString("category"),
                        rs.getString("edition"),
                        rs.getString("publisher")
                );

                books.add(book);
            }
        }

        return books;
    }

    
    public Book getBookById(int bookId) throws SQLException {

        String sql = "SELECT * FROM books WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Book(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("isbn"),
                            rs.getString("book_code"),
                            rs.getString("category"),
                            rs.getString("edition"),
                            rs.getString("publisher")
                    );
                }
            }
        }

        return null;
    }

    
    public boolean updateBook(Book book) throws SQLException {

        String sql = "UPDATE books SET " +
                "title = ?, author = ?, isbn = ?, book_code = ?, " +
                "category = ?, edition = ?, publisher = ? " +
                "WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getBookCode());
            ps.setString(5, book.getCategory());
            ps.setString(6, book.getEdition());
            ps.setString(7, book.getPublisher());
            ps.setInt(8, book.getBookId());

            return ps.executeUpdate() > 0;
        }
    }

    
    public boolean deleteBook(int bookId) throws SQLException {

        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            return ps.executeUpdate() > 0;
        }
    }

    
    public List<Inventory> searchAcrossLibraries(String keyword)
            throws SQLException {

        List<Inventory> results = new ArrayList<>();

        String sql = "SELECT " +
                "li.inventory_id, li.library_id, li.book_id, " +
                "li.total_copies, li.available_copies, li.book_price, " +
                "b.title, b.author, b.isbn, b.book_code, " +
                "b.category, b.edition, b.publisher, " +
                "l.library_name, l.area, l.address, " +
                "l.contact_number, l.email, l.description " +
                "FROM library_inventory li " +
                "INNER JOIN books b ON li.book_id = b.book_id " +
                "INNER JOIN libraries l ON li.library_id = l.library_id " +
                "WHERE b.title LIKE ? " +
                "OR b.author LIKE ? " +
                "OR b.isbn LIKE ? " +
                "OR b.book_code LIKE ? " +
                "OR b.category LIKE ? " +
                "ORDER BY b.title, l.library_name";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String searchValue = "%" + keyword.trim() + "%";

            for (int i = 1; i <= 5; i++) {
                ps.setString(i, searchValue);
            }

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Book book = new Book(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("isbn"),
                            rs.getString("book_code"),
                            rs.getString("category"),
                            rs.getString("edition"),
                            rs.getString("publisher")
                    );

                    Library library = new Library(
                            rs.getInt("library_id"),
                            rs.getString("library_name"),
                            rs.getString("area"),
                            rs.getString("address"),
                            rs.getString("contact_number"),
                            rs.getString("email"),
                            rs.getString("description")
                    );

                    Inventory inventory = new Inventory(
                            rs.getInt("inventory_id"),
                            rs.getInt("library_id"),
                            rs.getInt("book_id"),
                            rs.getInt("total_copies"),
                            rs.getInt("available_copies"),
                            rs.getBigDecimal("book_price")
                    );

                    inventory.setBook(book);
                    inventory.setLibrary(library);

                    results.add(inventory);
                }
            }
        }

        return results;
    }


    public boolean saveInventory(Inventory inventory)
            throws SQLException {

        String sql = "INSERT INTO library_inventory " +
                "(library_id, book_id, total_copies, " +
                "available_copies, book_price) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "total_copies = VALUES(total_copies), " +
                "available_copies = VALUES(available_copies), " +
                "book_price = VALUES(book_price)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, inventory.getLibraryId());
            ps.setInt(2, inventory.getBookId());
            ps.setInt(3, inventory.getTotalCopies());
            ps.setInt(4, inventory.getAvailableCopies());

            if (inventory.getBookPrice() == null) {
                ps.setNull(5, Types.DECIMAL);
            } else {
                ps.setBigDecimal(5, inventory.getBookPrice());
            }

            return ps.executeUpdate() > 0;
        }
    }
}
