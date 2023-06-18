package repository;

import model.Librarian;

import java.util.List;
import java.util.Optional;

public interface LibrarianRepo {
    void addLibrarian(Librarian librarian);
    Optional<Librarian> getUser(String id);
    void update(Librarian librarian);
    List<Librarian> getAllLibrarian();
    void removeLibrarian(Librarian librarian);
}
