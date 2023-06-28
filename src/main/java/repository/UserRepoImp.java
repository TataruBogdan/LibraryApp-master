package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepoImp implements UserRepo {

    private final List<User> usersList;

    private static UserRepoImp userRepoImp;

    private UserRepoImp() {
        usersList = new ArrayList<>();

    }

    public static UserRepoImp getInstance() {
        if (userRepoImp == null) {
            userRepoImp = new UserRepoImp();
        }
        return userRepoImp;
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
