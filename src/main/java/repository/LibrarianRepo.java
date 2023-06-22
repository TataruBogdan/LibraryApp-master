package repository;

import model.Librarian;

import java.util.List;
import java.util.Optional;

public interface LibrarianRepo {
    boolean addLibrarian(Librarian librarian);
    Optional<Librarian> getLIbrarian(String id);
    void update(Librarian librarian);
    List<Librarian> getAllLibrarian();
    boolean removeLibrarian(Librarian librarian);
}
