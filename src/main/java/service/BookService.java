package service;

import model.Book;
import repository.LibraryBookRepo;

import java.util.Date;
import java.util.Optional;

public class BookService {

    private LibraryBookRepo libraryBookRepo = LibraryBookRepo.getInstance();


// Todo
//  Check in main for the optional.isPresent
    public Optional<Book> checkBook(String bookName) {
       return libraryBookRepo.getBook(bookName);
    }

    public Optional<Book> addBook(String bookName, String author, String ISBN, String genre, Date date) {
        Book book = new Book(bookName, author, ISBN, genre, date);
        boolean addedBook = libraryBookRepo.addBook(book);
        if(addedBook) {
            return Optional.of(book);
        }
        return Optional.empty();
    }
}
