package ui;

import domain.applicantsmatcher.ApplicantsMatcher;
import domain.applicantsmatcher.ArrowTechApplicantsMatcher;
import domain.authentication.ArrowTechAuthentication;
import domain.authentication.AuthenticationHandler;
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
    private  final SecurityScan securityScan;
    private final DatabaseHandler databaseHandler;

    public SimpleUserInterface() {
        logger = new ConsoleLogger();
        applicantsMatcher = new ArrowTechApplicantsMatcher();
        authenticationHandler = new ArrowTechAuthentication();
        databaseHandler = new ArrowTechDatabase();
        securityScan = new ArkSecurityScan("James");
        logger.log("Initializing UI...");
    }

    @Override
    public void launch() {
        showWelcomeMessage();
        while(true) {
            boolean isUserAuthenticationSuccessful = authenticateUser();
            if (isUserAuthenticationSuccessful) {
                logger.log("Authentication successful");
                presentMenu();
                authenticationHandler.logout();
            } else {
                logger.log("Authentication not successful");
            }
            System.out.println("Do you want to continue using the system? (Y/N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")) {
                break;
            }
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
            System.out.println("Enter 1 to run applicants matcher feature");
            System.out.println("Enter 2 to perform security scan");
            System.out.println("Enter 0 to log out");
            selection = scanner.nextInt();
            scanner.nextLine();
            if (selection == 1) {
                applicantsMatcher();
            } else if (selection == 2) {
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
                List<String> unauthorizedAttempts = securityScan.unauthorizedAttempts();
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
                System.out.println("Security admin name: ");
                String adminName = scanner.nextLine();
                System.out.println("Enter the date you want the report for (MM-dd-yyyy) ");
                String reportDate = scanner.nextLine();
                String report = securityScan.printReport(adminName, reportDate);
                System.out.println(report);
            }
        }
    }

    private void applicantsMatcher() {
        System.out.println("Enter 1 to parse resume");
        System.out.println("Enter 2 to match job openings");
        int selection = scanner.nextInt();
        scanner.nextLine();
        if (selection == 1) {
            parseResume();
        } else if (selection == 2){
            matchJobOpenings();
        }
    }

    private void matchJobOpenings() {
        StringBuilder sb = new StringBuilder("Here is a list of jobs:\n");
        List<DatabaseHandler.Job> jobs = applicantsMatcher.getJobOpenings();
        for (DatabaseHandler.Job job : jobs) {
            sb.append(job.jobTitle).append("\n");
        }
        System.out.println(sb);
        System.out.println("Enter the job title you want to see the requirement for: ");
        String jobTitle = scanner.nextLine();
        DatabaseHandler.Job job = null;
        for (DatabaseHandler.Job j : jobs) {
            if (j.jobTitle.equals(jobTitle)) {
                job = j;
            }
        }
        if(job != null) {
            String experience = applicantsMatcher.getExperiencesNeededForAJob(job);
            System.out.println(experience);
            System.out.println("Here are the candidates that are qualified for the role: ");
            List<DatabaseHandler.JobApplicant> qualifiedApplicants = applicantsMatcher.getCandidatesQualifiedForJob(job);
            for (DatabaseHandler.JobApplicant candidate : qualifiedApplicants) {
                System.out.println(candidate.name);
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
        List<DatabaseHandler.Resume> resumes = databaseHandler.getResumes();
        System.out.println("Here are the resumes found in the database:");
        for (DatabaseHandler.Resume resume : resumes) {
            System.out.println(resume.fileName);
        }
        System.out.println("Enter the file name of the resume that you want to parse");
        String resumeFileName = scanner.nextLine();
        DatabaseHandler.Resume resume = null;
        for (DatabaseHandler.Resume r : resumes) {
            if (r.fileName.equals(resumeFileName)) {
                resume = r;
            }
        }
        if (resume != null) {
            ApplicantsMatcher.ParsedResumeInfo parsedResume = applicantsMatcher.parseResume(resume);
            System.out.println("Information parsed from the resume: ");
            System.out.println("Applicant's name: " + parsedResume.applicantName);
            System.out.println("Applicant's contact number: " + parsedResume.contactNumber);
            System.out.println("Applicant's email address: " + parsedResume.emailAddress);
            System.out.println("Applican't education level: " + parsedResume.educationLevel);
            System.out.println("Do you want to flag the resume? Y/N ");
            String flagResume = scanner.nextLine();
            if (flagResume.equalsIgnoreCase("Y")) {
                System.out.println("Select 1 to flag the resume as OverQualified");
                System.out.println("Select 2 to flag the resume as UnderQualified ");
                System.out.println("Select 3 to flag the resume as Potential Match ");
                int flagType = scanner.nextInt();
                scanner.nextLine();
                ApplicantsMatcher.ResumeFlagType value = ApplicantsMatcher.ResumeFlagType.values()[flagType - 1];
                applicantsMatcher.flagResume(resume, value);
            }
        } else {
            System.out.println("Resume not found");
        }
    }

    private void showWelcomeMessage() {
        System.out.println("Hi, Welcome to ArrowTech system");
    }
}
