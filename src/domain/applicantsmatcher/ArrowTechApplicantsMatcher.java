package domain.applicantsmatcher;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;
import technicalservices.persistence.PersistenceHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ArrowTechApplicantsMatcher implements ApplicantsMatcher {

    private final PersistenceHandler database;
    private final Logger logger;
    private final Map<String, ParsedResumeInfo> parsedResumeInfoMap;

    public ArrowTechApplicantsMatcher() {
        logger = new ConsoleLogger();
        database = PersistenceHandler.createDatabaseHandler();
        parsedResumeInfoMap = new HashMap<>();
        initializeParsedResumes();
        logger.log("Initializing applicants matcher");
    }

    @Override
    public List<PersistenceHandler.Job> getJobOpenings() {
        return database.getJobs();
    }

    @Override
    public String getExperiencesNeededForAJob(PersistenceHandler.Job job) {
        return "Education level: " + job.requiredEducationLevel +
                "\nNumber of experiences required: " + job.requiredNumberOfExperiences +
                "\nRequired location for the job: " + job.preferredLocation;
    }

    @Override
    public List<PersistenceHandler.JobApplicant> getCandidatesQualifiedForJob(PersistenceHandler.Job job) {
        List<PersistenceHandler.JobApplicant> jobApplicants = new ArrayList<>();
        for (PersistenceHandler.JobApplicant applicant : database.getJobApplicants()) {
            if (job.preferredLocation.equalsIgnoreCase(applicant.location)
            && job.requiredNumberOfExperiences.equalsIgnoreCase(applicant.numberOfExperiences)
            && job.requiredEducationLevel.equalsIgnoreCase(applicant.educationLevel)) {
                jobApplicants.add(applicant);
            }
        }
        return jobApplicants;
    }

    @Override
    public void requestCandidatesToApplyForJob(PersistenceHandler.Job job, List<PersistenceHandler.JobApplicant> jobApplicants) {
        StringBuilder request = new StringBuilder("Request to apply for " + job.jobTitle + " sent to ");
        for (PersistenceHandler.JobApplicant applicant : jobApplicants) {
            request.append(applicant.name).append(", ");
        }
        logger.log(request.toString());
    }

    @Override
    public ParsedResumeInfo parseResume(PersistenceHandler.Resume resume) {
        logger.log("Parsing the resume...");
        return parsedResumeInfoMap.get(resume.fileName);
    }

    @Override
    public void flagResume(PersistenceHandler.Resume resume, ResumeFlagType flagType) {
        if (flagType == ResumeFlagType.OVERQUALIFIED) {
            resume.tagName = "Overqualified";
        } else if (flagType == ResumeFlagType.UNDERQUALIFIED) {
            resume.tagName = "Underqualified";
        } else if (flagType == ResumeFlagType.POTENTIAL_MATCH) {
            resume.tagName = "Potential match";
        }
        logger.log("Resume has been flagged as " + flagType);
    }

    @Override
    public List<PersistenceHandler.Resume> getResumes() {
        return database.getResumes();
    }

    private void initializeParsedResumes() {
        List<PersistenceHandler.Resume> resumes = database.getResumes();
        List<ParsedResumeInfo> parsedResumes = new ArrayList<>();
        parsedResumes.add(new ParsedResumeInfo("John", "Bachelors", "john@gmail.com", "123-456-789"));
        parsedResumes.add(new ParsedResumeInfo("Josh", "Masters", "josh@gmail.com", "128-956-789"));
        parsedResumes.add(new ParsedResumeInfo("Eric", "Bachelors", "eric@gmail.com", "163-856-589"));
        for (int i = 0; i < parsedResumes.size(); i++) {
            parsedResumeInfoMap.put(resumes.get(i).fileName, parsedResumes.get(i));
        }
    }
}
