package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BorrowedBook implements Subject {

    private User user;
    private Book book;
    private LocalDate dueDate;
    private LocalDate borrowDate;
    private boolean hasFine;
    private double fineAmount;
    private List<Observer> observerList;

    public BorrowedBook(User user, Book book, LocalDate dueDate) {
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
        this.borrowDate = LocalDate.now();
        this.hasFine = false;
        this.fineAmount = 0.00;
        this.observerList = new ArrayList<>();
    }

    //TODO
    // SetUser must be private ? cannot change user ? only if needed ?
    private void setUser(User user) {
        this.user = user;
    }

    //TODO
    // setBook must be private ? cannot change book ? only if needed ?
    private void setBook(Book book) {
        this.book = book;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    private void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setHasFine(boolean hasFine) {
        this.hasFine = hasFine;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public boolean isHasFine() {
        return hasFine;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "user=" + user +
                ", book=" + book +
                ", dueDate=" + dueDate +
                ", borrowDate=" + borrowDate +
                ", hasFine=" + hasFine +
                ", fineAmount=" + fineAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBook that = (BorrowedBook) o;
        return Objects.equals(user, that.user) && Objects.equals(book, that.book) && Objects.equals(dueDate, that.dueDate) && Objects.equals(borrowDate, that.borrowDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book, dueDate, borrowDate);
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
