package service;

import model.*;
import repository.LibraryBorrowedBookRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// implement singleton pattern because we need only one instance of class
public class BorrowedBookService implements Subject {


    private LibraryBorrowedBookRepo libraryBorrowedBookRepo;

    //change this to static
    BookService bookService = BookService.getInstance();

    private List<Observer> observerList;

    private static BorrowedBookService borrowedBookService;

    private BorrowedBookService() {
        this.libraryBorrowedBookRepo = new LibraryBorrowedBookRepo();
        //this.bookService = new BookService();
        this.observerList = new ArrayList<>();
    }

    public static BorrowedBookService getInstance() {
        if (borrowedBookService == null) {
            borrowedBookService = new BorrowedBookService();
        }
        return borrowedBookService;
    }

    // we checked that the book exists -> weed to borrow the book to the USER
    public Optional<Book> borrowBook(User user, String bookName) {
        Book book = null;

        // retrieve the book id based on name ???
        Optional<Book> optionalBook = bookService.checkBook(bookName);

        LocalDate dueDate = LocalDate.now().plusMonths(1);

        if (optionalBook.isPresent() && optionalBook.get().isAvailable()) {
            book = optionalBook.get();
            book.setAvailable(!book.isAvailable());

            BorrowedBook borrowedBook = new BorrowedBook(user, book, dueDate);
            libraryBorrowedBookRepo.addBorrowedBook(borrowedBook);
        }

        return Optional.ofNullable(book);
    }

    public boolean renewBorrowedBook(User user, Long idBorrowedBook) {

        Optional<BorrowedBook> foundBB = user.getUserListOfBorrowedBooks()
                .stream()
                .filter(borrowedBook -> borrowedBook.getBook().getID() == idBorrowedBook)
                .findFirst();

        LocalDate nowDate;
        LocalDate dueDate;

        if (foundBB.isPresent()) {
            nowDate = LocalDate.now();
            dueDate = LocalDate.now().plusWeeks(2);

            if (foundBB.get().getDueDate().isBefore(nowDate)) {
                foundBB.get().setDueDate(dueDate);
                return true;
            } else {
                this.notifyLibrarians("Please check due date and book");
                //librarian.notifyLibrarians(foundBB.get());
                return false;
            }
        }
        return false;
    }

    public boolean removeBorrowedBook(User user, Long bookId) {

        Optional<BorrowedBook> foundBB = user.getUserListOfBorrowedBooks()
                .stream()
                .filter(borrowedBook -> borrowedBook.getBook().getID() == bookId)
                .findFirst();

        if (foundBB.isPresent()) {
            foundBB.get().getBook().setAvailable(true);
            user.deleteBook(foundBB.get());
            return true;
        }
        return false;
    }


    @Override
    public void attachLibrarian(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detachLibrarian(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyLibrarians(String notification) {
        observerList.forEach(observer -> observer.notify(notification));
    }

//    public BorrowedBook returnBorrowedBook(long idBorrowedBook) {
//
//    }
}
