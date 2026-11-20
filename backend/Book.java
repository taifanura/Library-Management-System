
public class Book {

    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private String bookCode;
    private String category;
    private String edition;
    private String publisher;

    public Book() {
    }

    public Book(int bookId, String title, String author,
                String isbn, String bookCode,
                String category, String edition,
                String publisher) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.bookCode = bookCode;
        this.category = category;
        this.edition = edition;
        this.publisher = publisher;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
