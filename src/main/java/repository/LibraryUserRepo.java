package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryUserRepo implements UserRepo {

    private final List<User> usersList;

    private static LibraryUserRepo libraryUserRepo;

    private LibraryUserRepo() {
        usersList = new ArrayList<>();

    }

    public static LibraryUserRepo getInstance() {
        if (libraryUserRepo == null) {
            libraryUserRepo = new LibraryUserRepo();
        }
        return libraryUserRepo;
    }

    @Override
    public boolean addUser(User user) {
        return usersList.add(user);
    }

    @Override
    public Optional<User> getUserId(long id) {
        return usersList.stream().filter(user -> user.getID() == id).findFirst();
    }

    public Optional<User> getUserName(String name) {
        return usersList.stream()
                .filter(user -> user.getUserName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Optional<User> getUserEmail(String email) {
        return usersList.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findAny();
    }

    @Override
    public boolean removeUser(int id) {
        Optional<User> optionalUser = usersList.stream().filter(user -> user.getID() == id).findAny();

        return optionalUser.map(usersList::remove).orElse(false);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(usersList);
    }


}
