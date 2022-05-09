package domain.authentication;

public class ArrowTechMockLogin extends ArrowTechAuthentication {

    @Override
    public boolean login(String username, String password) {
        return true;
    }

}
