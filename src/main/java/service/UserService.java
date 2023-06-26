package service;

import model.User;
import repository.LibraryUserRepo;

import java.util.List;
import java.util.Optional;

public class UserService {

    private LibraryUserRepo libraryUserRepo = LibraryUserRepo.getInstance();

    public boolean addUser(String userName, String password, String email) {

        //TODO
        Optional<User> userOptional = libraryUserRepo.getUserEmail(email);
        if (userOptional.isEmpty()) {
            User user = new User(email, userName, password);
            return libraryUserRepo.addUser(user);
        }
        return false;
    }

    public boolean checkUserEmail(String email) {
        Optional<User> userEmail = libraryUserRepo.getUserEmail(email);
        return userEmail.isPresent();
    }

    public boolean checkUserName(String userName) {
        Optional<User> foundUserName = libraryUserRepo.getUserName(userName);
        return foundUserName.isPresent();
    }

    public Optional<User> getUser(long id) {
        return libraryUserRepo.getUserId(id);
    }

    public Optional<User> getUser(String name) {
        return  libraryUserRepo.getUserName(name);
    }

    public boolean removeUser(int id) {
        return libraryUserRepo.removeUser(id);
    }

    public List<User> getAllUsers() {
        return libraryUserRepo.getAllUsers();
    }

}
