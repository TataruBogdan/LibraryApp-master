package service;

import model.*;
import repository.BorrowedBookRepoImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// implement singleton pattern because we need only one instance of class
public class BorrowedBookService implements Subject {


    private BorrowedBookRepoImpl borrowedBookRepoImpl;

    //use Librarian service in place of LibraryLibrarianRepo
    private LibrarianService librarianService = new LibrarianService();

    // use User service in place of
    private UserService userService = new UserService();

    //change this to static
    private BookService bookService = new BookService();

    private List<Observer> observerList;


    public BorrowedBookService() {
        this.borrowedBookRepoImpl = BorrowedBookRepoImpl.getInstance();
        this.observerList = new ArrayList<>();
    }


    // we checked that the book exists -> we need to borrow the book to the USER
    public Optional<BorrowedBook> borrowBook(long userID, String bookName) {
        BorrowedBook borrowedBook = null;
        // retrieve the book id based on name ???
        Optional<Book> optionalBook = bookService.checkBook(bookName);

        LocalDate dueDate = LocalDate.now().plusMonths(1);
        if (optionalBook.isPresent() && optionalBook.get().isAvailable()) {

            Optional<User> userOptional = userService.getUser(userID);

            if (userOptional.isPresent()) {
                optionalBook.get().setAvailable(false);
                borrowedBook = new BorrowedBook(userOptional.get(), optionalBook.get(), dueDate);
                userOptional.get().setBB(optionalBook.get());
                borrowedBookRepoImpl.addBorrowedBook(borrowedBook);
            }
        }
        if (optionalBook.isPresent()) {
            return Optional.ofNullable(borrowedBook);
        } else {
            return Optional.empty();
        }

    }

    public boolean renewBorrowedBook(long userId, Long idBorrowedBook) {

        LocalDate nowDate;
        LocalDate newDueDate;

        Optional<BorrowedBook> borrowedBook = borrowedBookRepoImpl.getBorrowedBook(idBorrowedBook);
        if (borrowedBook.isPresent()) {
            Optional<User> userOptional = userService.getUser(userId);
            if (userOptional.isPresent()) {
                nowDate = LocalDate.now();
                newDueDate = LocalDate.now().plusWeeks(2);
                if (nowDate.isBefore(borrowedBook.get().getDueDate())) {
                    borrowedBook.get().setDueDate(newDueDate);
                    return true;
                } else {
                    this.notifyLibrarians("Please check due date and book");
                    return false;
                }
            }else {
                System.out.println("User id doesn't exist");
            }
        } else {
            System.out.println("Book doesn't exist");
        }
        return false;
    }

    public boolean returnBorrowedBook(long user, Long bookId) {
        //remove Borrowed Book from BB DB also

        Optional<BorrowedBook> borrowedBook = borrowedBookRepoImpl.getBorrowedBook(bookId);

        if (borrowedBook.isPresent()) {
            Optional<User> userOptional = userService.getUser(user);
            if (userOptional.isPresent()) {
                borrowedBook.get().getBook().setAvailable(true);
                boolean removedBorrowedBook = borrowedBookRepoImpl.removeBorrowedBook(bookId);
                boolean bookUserRemoved = userOptional.get().deleteBook(borrowedBook.get().getBook());
                return bookUserRemoved && removedBorrowedBook;
            }
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
}
