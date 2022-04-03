package ui;

import domain.applicantsmatcher.ApplicantsMatcher;
import domain.applicantsmatcher.ArrowTechApplicantsMatcher;
import domain.authentication.ArrowTechAuthentication;
import domain.authentication.AuthenticationHandler;
import domain.resumeparser.ArrowTechResumeParser;
import domain.resumeparser.ParsedResumeInfo;
import domain.resumeparser.ResumeParser;
import domain.securityscan.ArkSecurityScan;
import domain.securityscan.SecurityScan;
import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;
import technicalservices.persistence.*;

import java.util.List;
import java.util.Scanner;

public class SimpleUserInterface implements UserInterface {

    private static final Scanner scanner = new Scanner(System.in);
    private final Logger logger;
    private final ApplicantsMatcher applicantsMatcher;
    private final AuthenticationHandler authenticationHandler;
    private final ResumeParser resumeParser;
    private  final SecurityScan securityScan;
    private final DatabaseHandler databaseHandler;

    public SimpleUserInterface() {
        logger = new ConsoleLogger();
        applicantsMatcher = new ArrowTechApplicantsMatcher();
        authenticationHandler = new ArrowTechAuthentication();
        resumeParser = new ArrowTechResumeParser();
        databaseHandler = new ArrowTechDatabase();
        securityScan = new ArkSecurityScan("James");
        logger.log("Initializing UI...");
    }

    @Override
    public void launch() {
        showWelcomeMessage();
        boolean isUserAuthenticationSuccessful = authenticateUser();
        if (isUserAuthenticationSuccessful) {
            logger.log("Authentication successful");
            presentMenu();
            authenticationHandler.logout();
        } else {
            logger.log("Authentication not successful");
        }
    }

    private boolean authenticateUser() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Enter password: ");
        String password = scanner.nextLine().trim();
        return authenticationHandler.login(username, password);
    }

    private void presentMenu() {
        int selection = -1;
        while(selection != 0) {
            System.out.println("Enter 1 to parse resumes");
            System.out.println("Enter 2 to match job openings");
            System.out.println("Enter 3 to perform security scan");
            System.out.println("Enter 0 to quit ");
            selection = scanner.nextInt();
            scanner.nextLine();
            if (selection == 1) {
                parseResume();
            } else if (selection == 2) {
                matchJobOpenings();
            } else if (selection == 3) {
                performSecurityScan();
            }
        }
    }

    private void performSecurityScan() {
        int selection = -1;
        while(selection != 0) {
            System.out.println("Enter 1 to see unauthorized events for today");
            System.out.println("Enter 2 to perform scan");
            System.out.println("Enter 3 to print report");
            System.out.println("Enter 0 to quit ");
            selection = scanner.nextInt();
            scanner.nextLine();
            if (selection == 1) {
                List<String> unauthorizedAttempts = securityScan.unauthorizedAttempts(1);
                if (unauthorizedAttempts.size() > 0) {
                    for (String s : unauthorizedAttempts) {
                        System.out.println(s);
                    }
                } else {
                    System.out.println("No unauthorized attempts found");
                }
            } else if (selection == 2) {
                System.out.println("Enter scan name: ");
                String scanName = scanner.nextLine();
                System.out.println("Enter scan type: ");
                String scanType = scanner.nextLine();
                System.out.println("Enter scan sensitivity level: ");
                String sensitivity = scanner.nextLine();
                String scan = securityScan.scan(scanName, scanType, sensitivity);
                System.out.println(scan);
            } else if (selection == 3) {
                String report = securityScan.printReport("James", "11-20-2020");
                System.out.println(report);
            }
        }
    }

    private void matchJobOpenings() {
        StringBuilder sb = new StringBuilder("Here is a list of jobs:\n");
        List<Job> jobs = applicantsMatcher.getJobOpenings();
        for (Job job : jobs) {
            sb.append(job.getJobTitle()).append("\n");
        }
        System.out.println(sb);
        System.out.println("Enter the job title you want to see the requirement for: ");
        String jobTitle = scanner.nextLine();
        Job job = null;
        for (Job j : jobs) {
            if (j.getJobTitle().equals(jobTitle)) {
                job = j;
            }
        }
        if(job != null) {
            String experience = applicantsMatcher.getExperiencesNeededForAJob(job);
            System.out.println(experience);
            System.out.println("Here are the candidates that are qualified for the role: ");
            List<JobApplicant> qualifiedApplicants = applicantsMatcher.getCandidatesQualifiedForJob(job);
            for (JobApplicant candidate : qualifiedApplicants) {
                System.out.println(candidate.getName());
            }
            if (qualifiedApplicants.size() > 0) {
                System.out.println("Do you want to send the candidates invitation to apply for job? (Y/N) ");
                String option = scanner.nextLine();
                if (option.equalsIgnoreCase("y")) {
                    applicantsMatcher.requestCandidatesToApplyForJob(job, qualifiedApplicants);
                }
            }
        } else {
            System.out.println("Job not found");
        }
    }

    private void parseResume() {
        List<Resume> resumes = databaseHandler.getResumes();
        System.out.println("Here are the resumes found in the database:");
        for (Resume resume : resumes) {
            System.out.println(resume.getFileName());
        }
        System.out.println("Enter the file name of the resume that you want to parse");
        String resumeFileName = scanner.nextLine();
        Resume resume = null;
        for (Resume r : resumes) {
            if (r.getFileName().equals(resumeFileName)) {
                resume = r;
            }
        }
        if (resume != null) {
            ParsedResumeInfo parsedResume = resumeParser.parseResume(resume);
            System.out.println("Information parsed from the resume: ");
            System.out.println("Applicant's name: " + parsedResume.getApplicantName());
            System.out.println("Applicant's contact number: " + parsedResume.getContactNumber());
            System.out.println("Applicant's email address: " + parsedResume.getEmailAddress());
            System.out.println("Applican't education level: " + parsedResume.getEducationLevel());
        } else {
            System.out.println("Resume not found");
        }
    }

    private void showWelcomeMessage() {
        System.out.println("Hi, Welcome to ArrowTech system");
    }
}
