package service;

import model.*;
import repository.LibraryBorrowedBookRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


// implement singleton pattern because we need only one instance of class
public class LibraryService implements Subject {


    private LibraryBorrowedBookRepo libraryBorrowedBookRepo;

    //use Librarian service in place of LibraryLibrarianRepo
    private LibrarianService librarianService = new LibrarianService();

    // use User service in place of
    private UserService userService = new UserService();

    //change this to static
    private BookService bookService = new BookService();

    private List<Observer> observerList;

    //private static LibraryService borrowedBookService;

    public LibraryService() {
        this.libraryBorrowedBookRepo = LibraryBorrowedBookRepo.getInstance();
        this.observerList = new ArrayList<>();
    }

    public boolean addUser(String userName, String password, String email) {

        boolean foundEmail = userService.checkUserEmail(email);
        boolean foundUserName = userService.checkUserName(userName);
        // first check if email exists, then check if userName exist
        if (foundEmail) {
            System.out.println("Email already used.");
            return false;
        } else if (foundUserName) {
            System.out.println("User already found.");
            return false;
        } else {
            return userService.addUser(userName, password, email);
        }
    }

    // we checked that the book exists -> weed to borrow the book to the USER
    public Optional<BorrowedBook> borrowBook(long userID, String bookName) {
        Book book;
        BorrowedBook borrowedBook = null;
        // retrieve the book id based on name ???
        Optional<Book> optionalBook = bookService.checkBook(bookName);

        LocalDate dueDate = LocalDate.now().plusMonths(1);
        if (optionalBook.isPresent() && optionalBook.get().isAvailable()) {

            Optional<User>userOptional = userService.getUser(userID);

            if (userOptional.isPresent()) {
                book = optionalBook.get();
                book.setAvailable(!book.isAvailable());
                borrowedBook = new BorrowedBook(userOptional.get(), book, dueDate);
                userOptional.get().setBB(book);
                //borrowedBook.setBook(book);
                libraryBorrowedBookRepo.addBorrowedBook(borrowedBook);
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
        LocalDate dueDate;

        Optional<BorrowedBook> borrowedBook = libraryBorrowedBookRepo.getBorrowedBook(idBorrowedBook);
        if (borrowedBook.isPresent()) {
            Optional<User> userOptional = userService.getUser(userId);
            if (userOptional.isPresent()) {
                long id = borrowedBook.get().getUser().getID();
                if (userOptional.get().getID() == id) {
                    nowDate = LocalDate.now();
                    dueDate = LocalDate.now().plusWeeks(2);
                    if (borrowedBook.get().getDueDate().isBefore(nowDate)){
                        borrowedBook.get().setDueDate(dueDate);
                        return true;
                    } else {
                        this.notifyLibrarians("Please check due date and book");
                        ///librarian.notifyLibrarians(foundBB.get());
                        return false;
                    }

                }
            }
        }

        return false;
    }

    public boolean returnBorrowedBook(long user, Long bookId) {
        //remove Borrowed Book from BB DB also

        Optional<BorrowedBook> borrowedBook = libraryBorrowedBookRepo.getBorrowedBook(bookId);
        if (borrowedBook.isPresent()) {
            Optional<User> userOptional = userService.getUser(user);
            if (userOptional.isPresent()) {
                boolean bookUserRemoved = userOptional.get().deleteBook(borrowedBook.get().getBook());
                boolean removedBorrowedBook = libraryBorrowedBookRepo.removeBorrowedBook(bookId);
                return bookUserRemoved && removedBorrowedBook;
            }
        }


        // remove book from User list
//        Optional<Book> foundBB = user.getUserListOfBorrowedBooks()
//                .stream()
//                .filter(borrowedBook -> borrowedBook.getID() == bookId)
//                .findFirst();
//
//        if (foundBB.isPresent()) {
//            foundBB.get().setAvailable(true);
//            user.deleteBook(foundBB.get());
//            return true;
//        }
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

    public boolean addBook(String book, String author, String ISBN, String genre, Date date) {
        Optional<Book> bookAdded = bookService.addBook(book, author, ISBN, genre, date);
        return bookAdded.isPresent();
    }

    public boolean bookExist(String name) {
        Optional<Book> book = bookService.checkBook(name);
        return book.isPresent();
    }

}
