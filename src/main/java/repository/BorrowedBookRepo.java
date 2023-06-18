package repository;

import model.BorrowedBook;

import java.util.List;
import java.util.Optional;

public interface BorrowedBookRepo {
    Optional<BorrowedBook> getBorrowedBook(long id);

    boolean addBorrowedBook(BorrowedBook borrowedBook);

    boolean removeBorrowedBook(Long bookId);

    boolean addBorrowedBooksList(List<BorrowedBook> borrowedBooks);



    List<BorrowedBook> getAllBorrowedBooks();
}
