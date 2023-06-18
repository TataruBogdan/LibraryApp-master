package repository;

// TODO Do we need the interface ?

public interface LogInAuthenticationRepo {
    boolean authenticationUser(String user,String password);
}
