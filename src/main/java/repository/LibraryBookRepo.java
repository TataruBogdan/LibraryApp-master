package repository;

import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryBookRepo implements BookRepo {


    private List<Book> books;

    private static LibraryBookRepo libraryBookRepo;

    private LibraryBookRepo() {
        books = new ArrayList<>();
    }


    public static LibraryBookRepo getInstance() {
        if (libraryBookRepo == null) {
            return libraryBookRepo = new LibraryBookRepo();
        }
        return libraryBookRepo;
    }


    @Override
    public Optional<Book> getBook(String bookName) {
        return books.stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName))
                .findFirst();
    }
    @Override
    public boolean addBook(Book book) {
        return books.add(book);
    }

    @Override
    public Optional<Book> searchBook(String name) {
        return books.stream().filter(book -> book.getName().equalsIgnoreCase(name)).findFirst();
    }

    // TODO
    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void removeBook(long id) {
        books.removeIf(bo -> bo.getID() == id);
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

}
