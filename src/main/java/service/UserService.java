package service;

import model.User;
import repository.LibraryUserRepo;

import java.util.List;
import java.util.Optional;

public class UserService {

    private LibraryUserRepo libraryUserRepo = LibraryUserRepo.getInstance();


    // add user must return something?
    public boolean addUser(User user) {
        return libraryUserRepo.addUser(user);
    }

    public Optional<User> getUser(int id) {
        return libraryUserRepo.getUser(id);
    }

    public Optional<User> getUser(String name) {
        return  libraryUserRepo.getUser(name);
    }

    public boolean removeUser(int id) {
        return libraryUserRepo.removeUser(id);
    }

    public List<User> getAllUsers() {
        return libraryUserRepo.getAllUsers();
    }

}
