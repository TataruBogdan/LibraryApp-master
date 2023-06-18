package service;

import model.Book;
import repository.LibraryBookRepo;

import java.util.Optional;

public class BookService {

    private LibraryBookRepo bookRepo;

    private static BookService bookService;

    private BookService() {
        bookRepo = new LibraryBookRepo();
    }


    public static BookService getInstance(){
        if (bookService == null){
            bookService = new BookService();
        }
        return bookService;
    }


// Todo
//  Check in main for the optional.isPresent
    public Optional<Book> checkBook(String bookName) {
       return bookRepo.getBook(bookName);
    }
}
