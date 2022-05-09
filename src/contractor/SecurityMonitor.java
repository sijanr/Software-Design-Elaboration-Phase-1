package contractor;

import java.util.ArrayList;
import java.util.List;

public abstract class SecurityMonitor {

    //static factory method
    static SecurityMonitor createSecurityMonitor(String adminName) {
        return new ArkSecurityMonitor(adminName);
    }

    //abstract classes
    abstract String login(String username, String password);
    abstract String logout();
    abstract List<String> unauthorizedAttempts();
    abstract String scan(String scanName, String scanType, String scanSensitivity);
    abstract String printReport(String securityAdminName, String dateForReport);

    //private implementation of the interface
    private static class ArkSecurityMonitor extends SecurityMonitor {

        private String securityAdminName;
        private static List<String> unsuccessfulAttempts = new ArrayList<>();
        private final List<User> users;

        ArkSecurityMonitor(String securityAdminName) {
            this.securityAdminName = securityAdminName;
            users = new ArrayList<>();
            users.add(createUser("Matt", "123456"));
            users.add(createUser("Jack", "qwerty"));
            users.add(createUser("Zoe", "asdf"));
        }

        @Override
        public String login(String username, String password) {
            for (User user : users) {
                if (user.username.equals(username) && user.password.equals(password)) {
                    return "Authorization successful";
                }
            }
            return "Authorization failed";
        }

        @Override
        public String logout() {
            return "Successfully logged out";
        }

        @Override
        public List<String> unauthorizedAttempts() {
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
            return "Performer: " + securityAdminName +
                    "\nDate: " + dateForReport +
                    "\nUnauthorized attempts: " + unsuccessfulAttempts.size() +
                    "\nMalware Detected: 0";
        }

        private User createUser(String username, String password) {
            User user = new User();
            user.username = username;
            user.password = password;
            return user;
        }
    }

    //struct
    private static class User {
        private String username;
        private String password;
    }
}
