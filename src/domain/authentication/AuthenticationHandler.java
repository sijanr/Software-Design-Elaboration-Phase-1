package domain.authentication;

public interface AuthenticationHandler {
    boolean login(String username, String password);
}
