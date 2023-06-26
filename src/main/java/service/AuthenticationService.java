package service;

import model.Librarian;
import model.User;
import repository.AuthenticationRepository;

import java.util.Optional;

public class AuthenticationService {

    private static AuthenticationRepository authenticationRepository = AuthenticationRepository.getInstance();

    private UserService userService = new UserService();

    public boolean authenticateUser(String username, String password) {
        Optional<User> authUser = authenticationRepository.getUser(username);

        if (authUser.isPresent()) {
            String authPassword = authUser.get().getPassword();
            String authUserName = authUser.get().getUserName();

            if (authUserName.equals(username) && authPassword.equals(password)){

                return true;
            }


        }
        return false;
    }

    // pass all fields
    public Optional<User> signUpUser(String email, String userName, String password) {

        userService.addUser(userName, password, email);
        return authenticationRepository.registerUser(email, userName, password);

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
