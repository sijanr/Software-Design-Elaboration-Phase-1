package domain.applicantsmatcher;

import technicalservices.persistence.DatabaseHandler;

import java.util.List;

public interface ApplicantsMatcher {
    List<DatabaseHandler.Job> getJobOpenings();
    String getExperiencesNeededForAJob(DatabaseHandler.Job job);
    List<DatabaseHandler.JobApplicant> getCandidatesQualifiedForJob(DatabaseHandler.Job job);
    void requestCandidatesToApplyForJob(DatabaseHandler.Job job, List<DatabaseHandler.JobApplicant> jobApplicants);
}
