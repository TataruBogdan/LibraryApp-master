package repository;

import model.BorrowedBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryBorrowedBookRepo implements BorrowedBookRepo {
    private final List<BorrowedBook> borrowedBooks;

    public LibraryBorrowedBookRepo() {
        borrowedBooks = new ArrayList<>();
    }

    @Override
    public Optional<BorrowedBook> getBorrowedBook(long id) {
         return borrowedBooks.stream().filter(borrowedBook -> borrowedBook.getBook().getID() == id ).findFirst();
    }

    @Override
    public boolean addBorrowedBook(BorrowedBook borrowedBook) {
        return borrowedBooks.add(borrowedBook);
    }

    @Override
    public boolean removeBorrowedBook(Long bookId) {

        Optional<BorrowedBook> foundBook = borrowedBooks.stream()
                .filter(borrowedBook -> borrowedBook.getBook().getID() == bookId)
                .findFirst();
        if (foundBook.isPresent()) {
            borrowedBooks.remove(foundBook.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addBorrowedBooksList(List<BorrowedBook> borrowedBooksList) {
        return borrowedBooks.addAll(borrowedBooksList);
    }

    @Override
    public List<BorrowedBook> getAllBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }


}
