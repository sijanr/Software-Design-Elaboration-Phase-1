package domain.applicantsmatcher;

import technicalservices.persistence.Job;
import technicalservices.persistence.JobApplicant;

import java.util.List;

public interface ApplicantsMatcher {
    List<Job> getJobOpenings();
    String getExperiencesNeededForAJob(Job job);
    List<JobApplicant> getCandidatesQualifiedForJob(Job job);
    void requestCandidatesToApplyForJob(Job job, List<JobApplicant> jobApplicants);
}
