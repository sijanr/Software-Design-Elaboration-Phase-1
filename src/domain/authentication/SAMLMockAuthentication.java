package domain.authentication;

class SAMLMockAuthentication extends ArrowTechAuthentication {

    @Override
    public boolean login(String username, String password) {
        return true;
    }

}
