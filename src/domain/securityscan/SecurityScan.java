package domain.securityscan;

import java.util.List;

public interface SecurityScan {
    static SecurityScan createSecurityScan(String securityAdminName) {
        return new ArkSecurityScan(securityAdminName);
    }
    void unsuccessfulLogin(String username, String password);
    List<String> unauthorizedAttempts();
    String scan(String scanName, String scanType, String scanSensitivity);
    String printReport(String securityAdminName, String dateForReport);
}
