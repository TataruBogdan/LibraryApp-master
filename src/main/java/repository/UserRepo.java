package repository;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {

    boolean addUser(User user);
    Optional<User> getUserId(long id);

    Optional<User> getUserName(String name);
    boolean removeUser(int id);
    List<User> getAllUsers();

}
