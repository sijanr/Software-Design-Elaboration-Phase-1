package domain.applicantsmatcher;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;
import technicalservices.persistence.ArrowTechDatabase;
import technicalservices.persistence.DatabaseHandler;
import technicalservices.persistence.Job;
import technicalservices.persistence.JobApplicant;

import java.util.ArrayList;
import java.util.List;

public class ArrowTechApplicantsMatcher implements ApplicantsMatcher {

    private final DatabaseHandler database;
    private final Logger logger;

    public ArrowTechApplicantsMatcher() {
        logger = new ConsoleLogger();
        database = new ArrowTechDatabase();
        logger.log("Initializing applicants matcher");
    }

    @Override
    public List<Job> getJobOpenings() {
        return database.getJobs();
    }

    @Override
    public String getExperiencesNeededForAJob(Job job) {
        return "Education level: " + job.getRequiredEducationLevel() +
                "\nNumber of experiences required: " + job.getRequiredNumberOfExperiences() +
                "\nRequired location for the job: " + job.getPreferredLocation();
    }

    @Override
    public List<JobApplicant> getCandidatesQualifiedForJob(Job job) {
        List<JobApplicant> jobApplicants = new ArrayList<>();
        for (JobApplicant applicant : database.getJobApplicants()) {
            if (job.getPreferredLocation().equalsIgnoreCase(applicant.getLocation())
            && job.getRequiredNumberOfExperiences().equalsIgnoreCase(applicant.getNumberOfExperiences())
            && job.getRequiredEducationLevel().equalsIgnoreCase(applicant.getEducationLevel())) {
                jobApplicants.add(applicant);
            }
        }
        return jobApplicants;
    }

    @Override
    public void requestCandidatesToApplyForJob(Job job, List<JobApplicant> jobApplicants) {
        StringBuilder request = new StringBuilder("Request to apply for " + job.getJobTitle() + " sent to ");
        for (JobApplicant applicant : jobApplicants) {
            request.append(applicant.getName()).append(", ");
        }
        logger.log(request.toString());
    }
}
