package domain.authentication;

import technicalservices.persistence.PersistenceHandler;

class AppleAuthentication extends SAMLAuthentication {

    @Override
    public boolean login(String username, String password) {
        logger.log("Using apple login...");
        for (PersistenceHandler.User user : databaseHandler.getUsers()) {
            if (user.username.equals(username) && user.password.equals(password)) {
                logger.log("Apple Authentication successful");
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {
        logger.log("Signed out of Apple account");
    }
}
