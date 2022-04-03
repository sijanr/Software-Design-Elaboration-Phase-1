package domain.authentication;

import technicalservices.logger.ConsoleLogger;
import technicalservices.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArrowTechAuthentication implements AuthenticationHandler {

    private final List<User> users;
    private final Logger logger;

    public ArrowTechAuthentication() {
        logger = new ConsoleLogger();
        logger.log("Initializing authentication");
        users = new ArrayList<>();
        users.add(new User("Matt", "123456"));
        users.add(new User("Jack", "qwerty"));
        users.add(new User("Zoe", "asdf"));
    }

    @Override
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && Objects.equals(user.password, password)) {
                return true;
            }
        }
        return false;
    }

    private static class User {

        private final String username;
        private final String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

}
