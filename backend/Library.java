
public class Library {

    private int libraryId;
    private String libraryName;
    private String area;
    private String address;
    private String contactNumber;
    private String email;
    private String description;

    public Library() {
    }

    public Library(int libraryId, String libraryName,
                   String area, String address,
                   String contactNumber, String email,
                   String description) {

        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.area = area;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.description = description;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
