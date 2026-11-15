
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    // Add a new library
    public boolean addLibrary(Library library) throws SQLException {

        String sql = "INSERT INTO libraries " +
                "(library_name, area, address, contact_number, email, description) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, library.getLibraryName());
            ps.setString(2, library.getArea());
            ps.setString(3, library.getAddress());
            ps.setString(4, library.getContactNumber());
            ps.setString(5, library.getEmail());
            ps.setString(6, library.getDescription());

            return ps.executeUpdate() > 0;
        }
    }

    // View all libraries
    public List<Library> getAllLibraries() throws SQLException {

        List<Library> libraries = new ArrayList<>();

        String sql = "SELECT * FROM libraries ORDER BY library_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Library library = new Library(
                        rs.getInt("library_id"),
                        rs.getString("library_name"),
                        rs.getString("area"),
                        rs.getString("address"),
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getString("description")
                );

                libraries.add(library);
            }
        }

        return libraries;
    }

    // Find library by ID
    public Library getLibraryById(int libraryId) throws SQLException {

        String sql = "SELECT * FROM libraries WHERE library_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, libraryId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Library(
                            rs.getInt("library_id"),
                            rs.getString("library_name"),
                            rs.getString("area"),
                            rs.getString("address"),
                            rs.getString("contact_number"),
                            rs.getString("email"),
                            rs.getString("description")
                    );
                }
            }
        }

        return null;
    }

    // Update library information
    public boolean updateLibrary(Library library) throws SQLException {

        String sql = "UPDATE libraries SET " +
                "library_name = ?, area = ?, address = ?, " +
                "contact_number = ?, email = ?, description = ? " +
                "WHERE library_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, library.getLibraryName());
            ps.setString(2, library.getArea());
            ps.setString(3, library.getAddress());
            ps.setString(4, library.getContactNumber());
            ps.setString(5, library.getEmail());
            ps.setString(6, library.getDescription());
            ps.setInt(7, library.getLibraryId());

            return ps.executeUpdate() > 0;
        }
    }

    // Delete library
    public boolean deleteLibrary(int libraryId) throws SQLException {

        String sql = "DELETE FROM libraries WHERE library_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, libraryId);

            return ps.executeUpdate() > 0;
        }
    }
}
