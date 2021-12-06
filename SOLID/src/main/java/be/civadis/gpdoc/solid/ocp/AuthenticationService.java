package be.civadis.gpdoc.solid.ocp;

public class AuthenticationService implements login, logout{


    @Override
    public boolean login(String userName, String password) {
        return userName == "admin" && password == "admin";
    }

    @Override
    public boolean logout() {
        return true;
    }
}
