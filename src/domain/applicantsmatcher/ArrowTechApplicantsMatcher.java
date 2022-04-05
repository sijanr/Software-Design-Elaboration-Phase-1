package domain.applicantsmatcher;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;
import technicalservices.persistence.ArrowTechDatabase;
import technicalservices.persistence.DatabaseHandler;

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
    public List<DatabaseHandler.Job> getJobOpenings() {
        return database.getJobs();
    }

    @Override
    public String getExperiencesNeededForAJob(DatabaseHandler.Job job) {
        return "Education level: " + job.requiredEducationLevel +
                "\nNumber of experiences required: " + job.requiredNumberOfExperiences +
                "\nRequired location for the job: " + job.preferredLocation;
    }

    @Override
    public List<DatabaseHandler.JobApplicant> getCandidatesQualifiedForJob(DatabaseHandler.Job job) {
        List<DatabaseHandler.JobApplicant> jobApplicants = new ArrayList<>();
        for (DatabaseHandler.JobApplicant applicant : database.getJobApplicants()) {
            if (job.preferredLocation.equalsIgnoreCase(applicant.location)
            && job.requiredNumberOfExperiences.equalsIgnoreCase(applicant.numberOfExperiences)
            && job.requiredEducationLevel.equalsIgnoreCase(applicant.educationLevel)) {
                jobApplicants.add(applicant);
            }
        }
        return jobApplicants;
    }

    @Override
    public void requestCandidatesToApplyForJob(DatabaseHandler.Job job, List<DatabaseHandler.JobApplicant> jobApplicants) {
        StringBuilder request = new StringBuilder("Request to apply for " + job.jobTitle + " sent to ");
        for (DatabaseHandler.JobApplicant applicant : jobApplicants) {
            request.append(applicant.name).append(", ");
        }
        logger.log(request.toString());
    }
}
