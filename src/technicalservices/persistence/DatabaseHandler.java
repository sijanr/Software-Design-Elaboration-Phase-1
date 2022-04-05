package technicalservices.persistence;

import java.util.List;

public interface DatabaseHandler {

    class Job {
        public String jobTitle;
        public String jobDescription;
        public String requiredEducationLevel;
        public String requiredNumberOfExperiences;
        public String preferredLocation;

        public Job(String jobTitle, String jobDescription, String requiredEducationLevel, String requiredNumberOfExperiences, String preferredLocation) {
            this.jobTitle = jobTitle;
            this.jobDescription = jobDescription;
            this.requiredEducationLevel = requiredEducationLevel;
            this.requiredNumberOfExperiences = requiredNumberOfExperiences;
            this.preferredLocation = preferredLocation;
        }
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

    List<Resume> getResumes();
    List<Job> getJobs();
    List<JobApplicant> getJobApplicants();
}
