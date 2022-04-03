package technicalservices.persistence;

public class JobApplicant {
    private String name;
    private String educationLevel;
    private String numberOfExperiences;
    private String location;

    public JobApplicant(String name, String educationLevel, String numberOfExperiences, String location) {
        this.name = name;
        this.educationLevel = educationLevel;
        this.numberOfExperiences = numberOfExperiences;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getNumberOfExperiences() {
        return numberOfExperiences;
    }

    public void setNumberOfExperiences(String numberOfExperiences) {
        this.numberOfExperiences = numberOfExperiences;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
