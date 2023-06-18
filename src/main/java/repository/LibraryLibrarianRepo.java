package repository;

import model.Librarian;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryLibrarianRepo implements LibrarianRepo {


    private List<Librarian> librarians;

    private static LibraryLibrarianRepo libraryLibrarianRepo;

    private LibraryLibrarianRepo() {
        this.librarians = new ArrayList<>();
    }

    public static LibraryLibrarianRepo getInstance() {
        if(libraryLibrarianRepo == null) {
            libraryLibrarianRepo = new LibraryLibrarianRepo();
        }
        return libraryLibrarianRepo;
    }


    @Override
    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    @Override
    public Optional<Librarian> getUser(String id) {

        return librarians.stream()
                .filter(librarian -> librarian.getID().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public void update(Librarian librarian) {

    }

    @Override
    public List<Librarian> getAllLibrarian() {
        return null;
    }

    @Override
    public void removeLibrarian(Librarian librarian) {
        librarians.remove(librarian);
    }
}