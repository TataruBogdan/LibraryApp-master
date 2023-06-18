package repository;

import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryBookRepo implements BookRepo {


    private List<Book> books = new ArrayList<>();


    @Override
    public Optional<Book> getBook(String bookName) {
        return books.stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName))
                .findFirst();
    }
    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Optional<Book> searchBook(String name) {
        return books.stream().filter(book -> book.getName().equalsIgnoreCase(name)).findFirst();
    }

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
