package repository;

import model.Librarian;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthenticationRepository {

    private final List<User> usersList;
//    private List<Librarian> librarianList;




    public AuthenticationRepository() {
        usersList = new ArrayList<>();
//        librarianList = new ArrayList<>();
    }

    // Logic to fetch user from a database
    // Return the User object if found, otherwise empty optional
    public Optional<User> getUser(String username) {
        return usersList.stream().filter(user -> user.getUserName().equalsIgnoreCase(username)).findFirst();
    }

    public boolean registerUser(String email, String username, String password) {
        User newUser = new User(email, username, password);

        return usersList.add(newUser);
    }
}
