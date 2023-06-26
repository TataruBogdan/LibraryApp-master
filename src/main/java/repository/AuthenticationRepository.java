package repository;

import model.Librarian;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthenticationRepository {
    private final List<User> usersList;
    private final List<Librarian> librarianList;

    private static AuthenticationRepository authenticationRepository;

    private AuthenticationRepository() {
        usersList = new ArrayList<>();
        librarianList = new ArrayList<>();
    }

    public static AuthenticationRepository getInstance() {
        if (authenticationRepository == null) {
            return  authenticationRepository  = new AuthenticationRepository();
        }
        return authenticationRepository;
    }
    // Logic to fetch user from a database
    // Return the User object if found, otherwise empty optional
    public Optional<User> getUser(String username) {
        return usersList.stream()
                .filter(user -> user.getUserName().equalsIgnoreCase(username))
                .findFirst();
    }

    public Optional<User> registerUser(String email, String username, String password) {
        User newUser = new User(email, username, password);
        usersList.add(newUser);
        return Optional.of(newUser);

    }

    public Optional<Librarian> getLibrarian(String userName){
        return librarianList.stream()
                .filter(librarian -> librarian.getUserName().equalsIgnoreCase(userName))
                .findFirst();
        
    }
}
