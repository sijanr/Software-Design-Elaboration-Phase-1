package technicalservices.persistence;

public class Job {

    private String jobTitle;
    private String jobDescription;
    private String requiredEducationLevel;
    private String requiredNumberOfExperiences;
    private String preferredLocation;

    public Job(String jobTitle, String jobDescription, String requiredEducationLevel, String requiredNumberOfExperiences, String preferredLocation) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.requiredEducationLevel = requiredEducationLevel;
        this.requiredNumberOfExperiences = requiredNumberOfExperiences;
        this.preferredLocation = preferredLocation;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getRequiredEducationLevel() {
        return requiredEducationLevel;
    }

    public void setRequiredEducationLevel(String requiredEducationLevel) {
        this.requiredEducationLevel = requiredEducationLevel;
    }

    public String getRequiredNumberOfExperiences() {
        return requiredNumberOfExperiences;
    }

    public void setRequiredNumberOfExperiences(String requiredNumberOfExperiences) {
        this.requiredNumberOfExperiences = requiredNumberOfExperiences;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }
}
