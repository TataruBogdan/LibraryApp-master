package service;

import model.Book;
import repository.BookRepoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class BookService {

    private BookRepoImpl bookRepoImpl = BookRepoImpl.getInstance();


// Todo
//  Check in main for the optional.isPresent
    public Optional<Book> checkBook(String bookName) {
       return bookRepoImpl.getBook(bookName);
    }

    public Optional<Book> addBook(String bookName, String author, String ISBN, String genre, String date) {

        SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
        Book book = null;
        Date checkDate;
        boolean addedBook = false;
        try {
            checkDate = format.parse(date);
            book = new Book(bookName, author, ISBN, genre, checkDate);
            addedBook = bookRepoImpl.addBook(book);
        } catch (ParseException e) {
            System.out.println("Date not correct ! \n" +
                                       "example Date : jan-2015 ");
        }
        if(addedBook) {
            return Optional.of(book);
        }
        return Optional.empty();
    }
}
