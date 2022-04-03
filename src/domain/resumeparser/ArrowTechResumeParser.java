package domain.resumeparser;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;
import technicalservices.persistence.ArrowTechDatabase;
import technicalservices.persistence.DatabaseHandler;
import technicalservices.persistence.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrowTechResumeParser implements ResumeParser {

    private final Map<String, ParsedResumeInfo> parsedResumeInfoMap;
    private final DatabaseHandler database;
    private final Logger logger;

    public ArrowTechResumeParser() {
        database = new ArrowTechDatabase();
        logger = new ConsoleLogger();
        logger.log("Initializing resume parser");
        parsedResumeInfoMap = new HashMap<>();
        initializeParsedResumes();
    }

    @Override
    public ParsedResumeInfo parseResume(Resume resume) {
        logger.log("Parsing the resume...");
        return parsedResumeInfoMap.get(resume.getFileName());
    }

    private void initializeParsedResumes() {
        List<Resume> resumes = database.getResumes();
        List<ParsedResumeInfo> parsedResumes = new ArrayList<>();
        parsedResumes.add(new ParsedResumeInfo("John", "Bachelors", "john@gmail.com", "123-456-789"));
        parsedResumes.add(new ParsedResumeInfo("Josh", "Masters", "josh@gmail.com", "128-956-789"));
        parsedResumes.add(new ParsedResumeInfo("Eric", "Bachelors", "eric@gmail.com", "163-856-589"));
        for (int i = 0; i < parsedResumes.size(); i++) {
            parsedResumeInfoMap.put(resumes.get(i).getFileName(), parsedResumes.get(i));
        }
    }
}
