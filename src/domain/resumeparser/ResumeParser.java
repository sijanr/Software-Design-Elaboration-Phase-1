package domain.resumeparser;

import technicalservices.persistence.Resume;

import java.util.List;

public interface ResumeParser {
    ParsedResumeInfo parseResume(Resume resume);
}
