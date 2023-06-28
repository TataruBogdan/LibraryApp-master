package service;

import model.BorrowedBook;
import model.Librarian;
import model.User;
import repository.LibrarianRepoImpl;

import java.util.Optional;

public class LibrarianService {
    private LibrarianRepoImpl librarianRepoImpl = LibrarianRepoImpl.getInstance();

    public boolean addLibrarian(String userName, String id, String password) {

        Librarian librarian = new Librarian(userName, id, password);
        return librarianRepoImpl.addLibrarian(librarian);

    }

    public Optional<Librarian> getLibrarian(String id) {

        return librarianRepoImpl.getLIbrarian(id);
    }

    public boolean removeLibrarian(String librarianID) {
        Optional<Librarian> foundLibrarian = librarianRepoImpl.getLIbrarian(librarianID);
        if (foundLibrarian.isPresent()) {
            librarianRepoImpl.removeLibrarian(foundLibrarian.get());
            return true;
        }else {
            return false;
        }
    }

    public double calculateAndAddFine(BorrowedBook borrowedBook){
        return 0;
    }
    public void notifyUser(User user){

    }
}
