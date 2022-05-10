package domain.authentication;

import technicalservices.logger.Logger;
import technicalservices.persistence.PersistenceHandler;

class SAMLAuthentication implements AuthenticationHandler{

    protected final PersistenceHandler databaseHandler;
    protected final Logger logger;

    public SAMLAuthentication() {
        databaseHandler = PersistenceHandler.createDatabaseHandler();
        logger = Logger.createLogger();
    }

    @Override
    public boolean login(String username, String password) {
        logger.log("SAML authenticating...");
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
