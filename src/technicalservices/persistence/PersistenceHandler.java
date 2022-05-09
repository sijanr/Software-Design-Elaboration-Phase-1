package technicalservices.persistence;

import java.util.List;

public interface PersistenceHandler {

    class Job {
        public String jobTitle;
        public String jobDescription;
        public String requiredEducationLevel;
        public String requiredNumberOfExperiences;
        public String preferredLocation;
    }

    class JobApplicant {

        public String name;
        public String educationLevel;
        public String numberOfExperiences;
        public String location;

        public JobApplicant(String name, String educationLevel, String numberOfExperiences, String location) {
            this.name = name;
            this.educationLevel = educationLevel;
            this.numberOfExperiences = numberOfExperiences;
            this.location = location;
        }
    }

    class User {
        public String username;
        public String password;
    }

    class Resume {

        public String fileName;
        public String dateCreated;
        public String tagName;

        public Resume(String fileName, String dateCreated) {
            this.fileName = fileName;
            this.dateCreated = dateCreated;
            this.tagName = "";
        }
    }

    static PersistenceHandler createDatabaseHandler() {
        return new ArrowTechDatabase();
    }

    List<Resume> getResumes();
    List<Job> getJobs();
    List<JobApplicant> getJobApplicants();
    List<User> getUsers();
}
