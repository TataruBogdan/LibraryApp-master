package service;

import model.User;

import java.util.Optional;

public class AuthenticationService {

    //private static AuthenticationRepository authenticationRepository = AuthenticationRepository.getInstance();

    private UserService userService = new UserService();

    public boolean authenticateUser(String username, String password) {
        Optional<User> authUser = userService.getUser(username);

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
    public boolean signUpUser(String email, String userName, String password) {

        return userService.addUser(userName, password, email);

    }

//    public boolean authenticateLibrarian(String username, String password) {
//        Optional<User> user = userService.(username);
//        return user.map(librarian -> librarian.getPassword().equalsIgnoreCase(password)).orElse(false);
//    }
//
//    public Optional<Librarian> signUpLibrarian(String email, String username, String password) {
//
//        User user = new User(email, username, password);
//        return authenticationRepository.getLibrarian(username);
//    }
}
