
import java.math.BigDecimal;

public class Inventory {

    private int inventoryId;
    private int libraryId;
    private int bookId;
    private int totalCopies;
    private int availableCopies;
    private BigDecimal bookPrice;

    private Book book;
    private Library library;

    public Inventory() {
    }

    public Inventory(int inventoryId, int libraryId,
                     int bookId, int totalCopies,
                     int availableCopies, BigDecimal bookPrice) {

        this.inventoryId = inventoryId;
        this.libraryId = libraryId;
        this.bookId = bookId;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.bookPrice = bookPrice;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
