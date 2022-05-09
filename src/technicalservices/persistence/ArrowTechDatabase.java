package technicalservices.persistence;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;

import java.util.ArrayList;
import java.util.List;

class ArrowTechDatabase implements PersistenceHandler {

    private List<Resume> resumes;
    private List<Job> jobs;
    private List<User> users;
    private List<JobApplicant> jobApplicants;
    private final Logger logger;

    public ArrowTechDatabase() {
        logger = new ConsoleLogger();
        logger.log("Initializing database");
        initializeUsers();
        initializeResumes();
        initializeJobs();
        initializeJobApplicants();
    }

    @Override
    public List<Resume> getResumes() {
        return resumes;
    }

    @Override
    public List<Job> getJobs() {
        return jobs;
    }

    @Override
    public List<JobApplicant> getJobApplicants() {
        return jobApplicants;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    private void initializeUsers() {
        users = new ArrayList<>();
        users.add(createUser("Matt", "123456"));
        users.add(createUser("Jack", "qwerty"));
        users.add(createUser("Zoe", "asdf"));
    }

    private void initializeJobApplicants() {
        logger.log("Initializing job applicants in the database");
        jobApplicants = new ArrayList<>();
        jobApplicants.add(new JobApplicant("Garnett", "Bachelors", "3", "Fullerton"));
        jobApplicants.add(new JobApplicant("Jessica", "Bachelors", "2", "Placentia"));
        jobApplicants.add(new JobApplicant("Romaine", "Masters", "3", "Fullerton"));
        jobApplicants.add(new JobApplicant("Braidy", "Masters", "5", "Los Angeles"));
        jobApplicants.add(new JobApplicant("Keane", "Bachelors", "1", "Fullerton"));
    }

    private void initializeJobs() {
        logger.log("Initializing jobs in the database");
        jobs = new ArrayList<>();
        jobs.add(createJob("Software Engineer", "Use C++ and Java to create products for the company", "Masters", "5", "Los Angeles"));
        jobs.add(createJob("Business Analyst", "Suggest actions for the business to make profits", "Bachelors", "3", "Fullerton"));
    }

    private void initializeResumes() {
        logger.log("Initializing resumes in the database");
        resumes = new ArrayList<>();
        resumes.add(new Resume("Resume1", "11/25/2018"));
        resumes.add(new Resume("Resume2", "04/45/2017"));
        resumes.add(new Resume("Resume3", "08/15/2020"));
    }

    private Job createJob(String jobTitle, String jobDescription, String requiredEducationLevel, String requiredNumberOfExperiences, String preferredLocation) {
        Job job = new Job();
        job.jobTitle = jobTitle;
        job.jobDescription = jobDescription;
        job.preferredLocation = preferredLocation;
        job.requiredEducationLevel = requiredEducationLevel;
        job.requiredNumberOfExperiences = requiredNumberOfExperiences;
        return job;
    }

    private User createUser(String username, String password) {
        User user = new User();
        user.username = username;
        user.password = password;
        return user;
    }
}
