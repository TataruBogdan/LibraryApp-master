package service;

import model.User;
import repository.UserRepoImp;

import java.util.List;
import java.util.Optional;

public class UserService {

    private UserRepoImp userRepoImp = UserRepoImp.getInstance();

    public boolean addUser(String userName, String password, String email) {


        //TODO
        Optional<User> userOptional = userRepoImp.getUserEmail(email);
        if (userOptional.isEmpty()) {
            User user = new User(email, userName, password);
            return userRepoImp.addUser(user);
        }
        return false;
    }

    public boolean checkUserEmail(String email) {
        Optional<User> userEmail = userRepoImp.getUserEmail(email);
        return userEmail.isPresent();
    }

    public boolean checkUserName(String userName) {
        Optional<User> foundUserName = userRepoImp.getUserName(userName);
        return foundUserName.isPresent();
    }

    public Optional<User> getUser(long id) {
        return userRepoImp.getUserId(id);
    }

    public Optional<User> getUser(String name) {
        return userRepoImp.getUserName(name);
    }

    public boolean removeUser(int id) {
        return userRepoImp.removeUser(id);
    }

    public List<User> getAllUsers() {
        return userRepoImp.getAllUsers();
    }

}
