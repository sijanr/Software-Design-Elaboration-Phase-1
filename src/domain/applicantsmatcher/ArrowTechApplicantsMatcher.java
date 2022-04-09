package domain.applicantsmatcher;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;
import technicalservices.persistence.ArrowTechDatabase;
import technicalservices.persistence.DatabaseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrowTechApplicantsMatcher implements ApplicantsMatcher {

    private final DatabaseHandler database;
    private final Logger logger;
    private final Map<String, ParsedResumeInfo> parsedResumeInfoMap;

    public ArrowTechApplicantsMatcher() {
        logger = new ConsoleLogger();
        database = new ArrowTechDatabase();
        parsedResumeInfoMap = new HashMap<>();
        initializeParsedResumes();
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

    @Override
    public ParsedResumeInfo parseResume(DatabaseHandler.Resume resume) {
        logger.log("Parsing the resume...");
        return parsedResumeInfoMap.get(resume.fileName);
    }

    @Override
    public void flagResume(DatabaseHandler.Resume resume, ResumeFlagType flagType) {
        if (flagType == ResumeFlagType.OVERQUALIFIED) {
            resume.tagName = "Overqualified";
        } else if (flagType == ResumeFlagType.UNDERQUALIFIED) {
            resume.tagName = "Underqualified";
        } else if (flagType == ResumeFlagType.POTENTIAL_MATCH) {
            resume.tagName = "Potential match";
        }
        logger.log("Resume has been flagged as " + flagType);
    }

    private void initializeParsedResumes() {
        List<DatabaseHandler.Resume> resumes = database.getResumes();
        List<ParsedResumeInfo> parsedResumes = new ArrayList<>();
        parsedResumes.add(new ParsedResumeInfo("John", "Bachelors", "john@gmail.com", "123-456-789"));
        parsedResumes.add(new ParsedResumeInfo("Josh", "Masters", "josh@gmail.com", "128-956-789"));
        parsedResumes.add(new ParsedResumeInfo("Eric", "Bachelors", "eric@gmail.com", "163-856-589"));
        for (int i = 0; i < parsedResumes.size(); i++) {
            parsedResumeInfoMap.put(resumes.get(i).fileName, parsedResumes.get(i));
        }
    }
}
