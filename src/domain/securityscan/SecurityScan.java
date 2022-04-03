package domain.securityscan;

import java.util.List;

public interface SecurityScan {
    void unsuccessfulLogin(String username, String password);
    List<String> unauthorizedAttempts(int sinceDays);
    String scan(String scanName, String scanType, String scanSensitivity);
    String printReport(String securityAdminName, String dateForReport);
}
