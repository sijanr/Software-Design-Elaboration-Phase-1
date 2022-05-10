package domain.applicantsmatcher;

import technicalservices.persistence.PersistenceHandler;

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

    static ApplicantsMatcher createApplicantsMatcher() {
        return new ArrowTechApplicantsMatcher();
    }

    List<PersistenceHandler.Job> getJobOpenings();
    String getExperiencesNeededForAJob(PersistenceHandler.Job job);
    List<PersistenceHandler.JobApplicant> getCandidatesQualifiedForJob(PersistenceHandler.Job job);
    void requestCandidatesToApplyForJob(PersistenceHandler.Job job, List<PersistenceHandler.JobApplicant> jobApplicants);
    ParsedResumeInfo parseResume(PersistenceHandler.Resume resume);
    void flagResume(PersistenceHandler.Resume resume, ResumeFlagType flagType);
    List<PersistenceHandler.Resume> getResumes();
}
