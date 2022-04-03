package domain.securityscan;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;

import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArkSecurityScan implements SecurityScan{

    private static List<String> unsuccessfulAttempts = new ArrayList<>();
    private String securityAdminName;
    private final Logger logger;

    public ArkSecurityScan(String securityAdminName) {
        logger = new ConsoleLogger();
        logger.log("Initializing security");
        this.securityAdminName = securityAdminName;
    }

    @Override
    public void unsuccessfulLogin(String username, String password) {
        Date date = Date.from(Instant.now());
        String dateString = DateFormat.getDateTimeInstance().format(date);
        String message = "Unauthorized login at " + dateString;
        unsuccessfulAttempts.add(message);
    }

    @Override
    public List<String> unauthorizedAttempts(int sinceDays) {
        return unsuccessfulAttempts;
    }

    @Override
    public String scan(String scanName, String scanType, String scanSensitivity) {
        if (scanName.length() > 7 && !scanType.equals("Q")) {
            return "Virus found";
        } else {
            return "No virus found";
        }
    }

    @Override
    public String printReport(String securityAdminName, String dateForReport) {
        return "Nothing to report";
    }
}
