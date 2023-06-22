package service;

import model.BorrowedBook;
import model.Librarian;
import model.User;
import repository.LibraryLibrarianRepo;

import java.util.Optional;

public class LibrarianService {
    private LibraryLibrarianRepo libraryLibrarianRepo = LibraryLibrarianRepo.getInstance();

    public boolean addLibrarian(String userName, String id, String password) {

        Librarian librarian = new Librarian(userName, id, password);
        return libraryLibrarianRepo.addLibrarian(librarian);

    }

    public Optional<Librarian> getLibrarian(String id) {

        return libraryLibrarianRepo.getLIbrarian(id);
    }

    public boolean removeLibrarian(String librarianID) {
        Optional<Librarian> foundLibrarian = libraryLibrarianRepo.getLIbrarian(librarianID);
        if (foundLibrarian.isPresent()) {
            libraryLibrarianRepo.removeLibrarian(foundLibrarian.get());
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
