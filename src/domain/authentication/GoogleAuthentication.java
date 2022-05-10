package domain.authentication;

import technicalservices.persistence.PersistenceHandler;

class GoogleAuthentication extends SAMLAuthentication {

    @Override
    public boolean login(String username, String password) {
        logger.log("Using google login...");
        for (PersistenceHandler.User user : databaseHandler.getUsers()) {
            if (user.username.equals(username) && user.password.equals(password)) {
                logger.log("Google Authentication successful");
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {
        logger.log("Signed out of Google account");
    }
}
