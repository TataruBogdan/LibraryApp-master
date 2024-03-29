package repository;

import model.Librarian;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibrarianRepoImpl implements LibrarianRepo {

    private List<Librarian> librarians;

    private static LibrarianRepoImpl librarianRepoImpl;

    private LibrarianRepoImpl() {
        this.librarians = new ArrayList<>();
    }

    public static LibrarianRepoImpl getInstance() {
        if(librarianRepoImpl == null) {
            librarianRepoImpl = new LibrarianRepoImpl();
        }
        return librarianRepoImpl;
    }


    @Override
    public boolean addLibrarian(Librarian librarian) {
        return librarians.add(librarian);
    }

    @Override
    public Optional<Librarian> getLIbrarian(String id) {

        return librarians.stream()
                .filter(librarian -> librarian.getID().equalsIgnoreCase(id))
                .findFirst();
    }

    //do we need update librarian?
    @Override
    public void update(Librarian librarian) {
        Librarian librarianToModify = librarians.get(librarians.indexOf(librarian));
        boolean containsLibrarian = librarians.contains(librarian);
        if(containsLibrarian) {
            librarians.remove(librarianToModify);
            librarians.add(librarian);
        }
    }

    @Override
    public List<Librarian> getAllLibrarian() {
        return new ArrayList<>(librarians);
    }

    @Override
    public boolean removeLibrarian(Librarian librarian) {
        return librarians.remove(librarian);
    }
}
