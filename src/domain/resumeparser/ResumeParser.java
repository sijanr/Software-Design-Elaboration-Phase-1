package domain.resumeparser;


import technicalservices.persistence.DatabaseHandler;

public interface ResumeParser {

    class ParsedResumeInfo {

        public String applicantName;
        public String educationLevel;
        public String emailAddress;
        public String contactNumber;

        public ParsedResumeInfo(String applicantName, String educationLevel, String emailAddress, String contactNumber) {
            this.applicantName = applicantName;
            this.educationLevel = educationLevel;
            this.emailAddress = emailAddress;
            this.contactNumber = contactNumber;
        }
    }

    enum ResumeFlagType {
        OVERQUALIFIED,
        UNDERQUALIFIED,
        POTENTIAL_MATCH
    }

    ParsedResumeInfo parseResume(DatabaseHandler.Resume resume);
    void flagResume(DatabaseHandler.Resume resume, ResumeFlagType flagType);
}
