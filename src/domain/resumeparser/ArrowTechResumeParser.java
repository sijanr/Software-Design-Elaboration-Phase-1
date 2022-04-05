package domain.resumeparser;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;
import technicalservices.persistence.ArrowTechDatabase;
import technicalservices.persistence.DatabaseHandler;

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
