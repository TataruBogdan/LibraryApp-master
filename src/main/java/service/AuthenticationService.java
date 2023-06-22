package service;

import model.Librarian;
import model.User;
import repository.AuthenticationRepository;

import java.util.Optional;

public class AuthenticationService {

    private static AuthenticationRepository authenticationRepository = AuthenticationRepository.getInstance();

    public boolean authenticateUser(String username, String password) {
        Optional<User> user = authenticationRepository.getUser(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    // pass all fields
    public boolean signUpUser(String email, String userName, String password) {

        User user = new User(email, userName, password);
        return authenticationRepository.registerUser(email, userName, password);
        //return authRepository.registerUser(email, userName, password);
    }

    public boolean authenticateLibrarian(String username, String password) {
        Optional<User> user = authenticationRepository.getUser(username);
        return user.map(librarian -> librarian.getPassword().equalsIgnoreCase(password)).orElse(false);
    }

    public Optional<Librarian> signUpLibrarian(String email, String username, String password) {

        User user = new User(email, username, password);
        return authenticationRepository.getLibrarian(username);
    }
}
