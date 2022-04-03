package technicalservices.persistence;

import java.util.List;

public interface DatabaseHandler {
    List<Resume> getResumes();
    List<Job> getJobs();
    List<JobApplicant> getJobApplicants();
}
