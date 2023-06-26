package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private static long nextID = 1;
    private long id = 1;
    private String email;
    private String userName;
    private String password;
    private final List<Book> userListOfBorrowedBooks;


    public User(String email, String userName, String password) {
        setEmail(email);
        setUserName(userName);
        setPassword(password);
        this.userListOfBorrowedBooks = new ArrayList<>();
        this.id = nextID++;
    }

    public long getID() {
        return id;
    }

    public void setEmail(String email) {
        if (email.contains("@") && email.contains(".")
                && email.indexOf("@") == email.lastIndexOf("@")
                && email.lastIndexOf("@") < email.lastIndexOf(".")) {
            this.email = email;
        } else {
            System.out.println("INVALID EMAIL!");
            this.email = userName + "@email.com";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setUserName(String userName) {
        if (!userName.trim().isEmpty()) {
            this.userName = userName;
        } else {
            System.out.println("INVALID USERNAME!");
            this.userName = "NO_USERNAME";
        }

    }

    public String getUserName() {
        return userName;
    }

    private void setPassword(String password) {
        if (!password.trim().isEmpty()) {
            this.password = password;
        } else {
            System.out.println("PLEASE ENTER A VALID PASSWORD!");
            this.password = "NO_PASSWORD";
        }
    }

    public String getPassword() {
        return password;
    }

    public List<Book> getUserListOfBorrowedBooks() {
        return new ArrayList<>(userListOfBorrowedBooks);
    }

    public boolean deleteBook(Book book) {
        return userListOfBorrowedBooks.remove(book);
    }

    public boolean setBB(Book book) {
         return userListOfBorrowedBooks.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + id + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userListOfBorrowedBooks=" + userListOfBorrowedBooks +
                '}';
    }
}
