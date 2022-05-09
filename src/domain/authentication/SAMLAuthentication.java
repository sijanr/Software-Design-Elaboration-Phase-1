package domain.authentication;

import technicalservices.logger.Logger;
import technicalservices.persistence.PersistenceHandler;

public class SAMLAuthentication implements AuthenticationHandler{

    private final PersistenceHandler databaseHandler;
    private final Logger logger;

    public SAMLAuthentication() {
        databaseHandler = PersistenceHandler.createDatabaseHandler();
        logger = Logger.createLogger();
    }

    @Override
    public boolean login(String username, String password) {
        for (PersistenceHandler.User user : databaseHandler.getUsers()) {
            if (user.username.equals(username) && user.password.equals(password)) {
                logger.log("Authentication successful");
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {
        logger.log("Logged out");
    }
}
