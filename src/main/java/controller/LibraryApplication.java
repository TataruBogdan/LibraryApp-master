package controller;

import model.Book;
import model.User;
import service.AuthenticationService;
import service.BorrowedBookService;

import java.util.Optional;
import java.util.Scanner;


public class LibraryApplication {

    //this is our instance of BBS
    static BorrowedBookService borrowedBookService = BorrowedBookService.getInstance();
    static AuthenticationService authenticationService = AuthenticationService.getInstance();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to the Library Application!");
        System.out.println("Please select an option:");
        System.out.println("1. Login as User");
        System.out.println("2. Login as Admin");
        int choice = scanner.nextInt();

        if (choice == 1) {
            loginAsUser();
        } else if (choice == 2) {
            loginAsAdmin();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }


        User newUser = new User("email@eamil.com","bobIsMyUserName", "bob1234abcd");


        borrowBook(newUser, "bookName");

        // check to see if it's a Long -> String
//        String userIdBookLog = "123456789L";
//        Long longId = Long.parseLong(userIdBookLog);
//        if (longId instanceof Long) {
//
//        }
        Long userIdBookLog = 123456789L;

        removeBorrowedBook(newUser, userIdBookLog);

        renewBook(newUser, userIdBookLog);
    }

    public static void loginAsUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login as User");
        System.out.println("Do you have an account? (yes/no)");
        String hasAccount = scanner.nextLine();

        if (hasAccount.equalsIgnoreCase("yes")) {
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();

            System.out.println("Please enter your password:");
            String password = scanner.nextLine();

            // Authenticate user
            boolean isAuthenticated = authenticationService.authenticateUser(username, password);

            if (isAuthenticated) {
                System.out.println("User authentication successful!");
            } else {
                System.out.println("User authentication failed. Invalid username or password.");
            }
        } else {
            System.out.println("Want to sign up? (yes/no)");
            String createAccount = scanner.nextLine();

            if (createAccount.equalsIgnoreCase("yes")) {
                signUp();
            } else {
                System.out.println("You need an account to login.");
            }

        }
    }

    public static void signUp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your email");
        String email = scanner.nextLine();

        System.out.println("Please enter your username");
        String userName = scanner.nextLine();

        System.out.println("Please enter your password");
        String password = scanner.nextLine();

        authenticationService.signUpUser(email, userName, password);
    }

//    TODO finish logic for the login as admin
    public static void loginAsAdmin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login as Admin");
        System.out.println("Do you have an account? (yes/no)");
        String hasAccount = scanner.nextLine();

        if (hasAccount.equalsIgnoreCase("yes")) {
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();

            System.out.println("Please enter your password:");
            String password = scanner.nextLine();

            // Authenticate admin
            boolean isAuthenticated = authenticationService.authenticateUser(username, password);

            if (isAuthenticated) {
                System.out.println("Admin authentication successful!");
                //TODO Continue with admin functionality
            } else {
                System.out.println("Admin authentication failed. Invalid username or password.");
            }
        } else {
            System.out.println("Account not found! Please try again.");
        }
    }

    public static void borrowBook(User user, String bookName) {
        Optional<Book> borrowBook = borrowedBookService.borrowBook(user, bookName);

        if (borrowBook.isPresent()) {
            System.out.println(borrowBook.get().getName() + " " + borrowBook.get().getAuthor()  + "borrowed");
        } else {
            System.out.println("Book " + bookName + " doesn't exists");
        }

    }
    public static void renewBook(User user, Long bookId) {
        boolean isBookRenewed = borrowedBookService.renewBorrowedBook(user, bookId);
        if (isBookRenewed) {
            System.out.println("Success ... Book renewed with 2 weeks more");
        } else {
            System.out.println("Cannot find book");
        }
    }

    /*
    TODO
      * We need to make the return book method.
      * The method below can be used by the librarian to remove the borrowed book from the user list of borrowed books when he wants to return a book.
    */

    public static void removeBorrowedBook(User user, Long bookID) {
        boolean bookToBeRemoved = borrowedBookService.removeBorrowedBook(user, bookID);

        if (bookToBeRemoved){
            System.out.println("Book successfully removed from your list");
        } else {
            System.out.println("Cannot find book in your list of borrowed books");
        }
    }
}
