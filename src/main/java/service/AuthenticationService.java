package service;

import model.Librarian;
import model.User;
import repository.AuthenticationRepository;
import repository.LibraryLibrarianRepo;
import repository.LibraryUserRepo;

import java.util.Optional;

public class AuthenticationService {

    //private AuthenticationRepository authRepository;
    private static AuthenticationService authenticationService;

    //TODO use inAuthenticationService -> LibraryUserRepo
    private LibraryUserRepo libraryUserRepo;
    private LibraryLibrarianRepo libraryLibrarianRepo;

    //use one instance of LibraryUserRepo
    LibraryUserRepo userRepo = LibraryUserRepo.getInstance();


    public AuthenticationService() {
        libraryUserRepo = LibraryUserRepo.getInstance();
        //this.authRepository = new AuthenticationRepository();

    }

    public static AuthenticationService getInstance() {
        if (authenticationService == null) {
            authenticationService = new AuthenticationService();
        }
        return authenticationService;
    }

    public boolean authenticateUser(String username, String password) {
        //Optional<User> user = authRepository.getUser(username);
        Optional<User> user = userRepo.getUser(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }


    // pass all fields
    public boolean signUpUser(String email, String userName, String password) {

        User user = new User(email, userName, password);
        return libraryUserRepo.addUser(user);
        //return authRepository.registerUser(email, userName, password);
    }

    public boolean authenticateLibrarian(String username, String password) {
        Optional<Librarian> user = libraryLibrarianRepo.getUser(username);
        return user.map(librarian -> librarian.getPassword().equalsIgnoreCase(password)).orElse(false);
    }

    public boolean signUpLibrarian(String email, String username, String password) {

        User user = new User(email, username, password);
        return libraryUserRepo.addUser(user);
    }
}
