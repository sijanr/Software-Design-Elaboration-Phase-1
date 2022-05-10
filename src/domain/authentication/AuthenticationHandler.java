package domain.authentication;

public interface AuthenticationHandler {
    static AuthenticationHandler createAuthenticationHandler(String authType) {
        if (authType.equals("SAMLAuthentication")) {
            return new SAMLAuthentication();
        } else {
            return new ArrowTechAuthentication();
        }
    }
    static AuthenticationHandler createSAMLAuthenticationHandler(String SAMLType) {
        if (SAMLType.equals("Apple")) {
            return new AppleAuthentication();
        } else if (SAMLType.equals("Google")){
            return new GoogleAuthentication();
        } else {
            return new SAMLMockAuthentication();
        }
    }
    boolean login(String username, String password);
    void logout();
}
