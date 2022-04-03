package technicalservices.persistence;

public class Resume {

    private String fileName;
    private String dateCreated;

    public Resume(String fileName, String dateCreated) {
        this.fileName = fileName;
        this.dateCreated = dateCreated;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
