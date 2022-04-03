package domain.resumeparser;

public class ParsedResumeInfo {

    private String applicantName;
    private String educationLevel;
    private String emailAddress;
    private String contactNumber;

    public ParsedResumeInfo(String applicantName, String educationLevel, String emailAddress, String contactNumber) {
        this.applicantName = applicantName;
        this.educationLevel = educationLevel;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
