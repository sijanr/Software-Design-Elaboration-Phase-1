package domain.applicantsmatcher;

import technicalservices.persistence.DatabaseHandler;

import java.util.List;

public interface ApplicantsMatcher {

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

    List<DatabaseHandler.Job> getJobOpenings();
    String getExperiencesNeededForAJob(DatabaseHandler.Job job);
    List<DatabaseHandler.JobApplicant> getCandidatesQualifiedForJob(DatabaseHandler.Job job);
    void requestCandidatesToApplyForJob(DatabaseHandler.Job job, List<DatabaseHandler.JobApplicant> jobApplicants);
    ParsedResumeInfo parseResume(DatabaseHandler.Resume resume);
    void flagResume(DatabaseHandler.Resume resume, ResumeFlagType flagType);
}
