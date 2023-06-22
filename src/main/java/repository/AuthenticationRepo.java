package repository;

// TODO Do we need the interface ?

public interface AuthenticationRepo {
    boolean authenticationUser(String user,String password);
}
