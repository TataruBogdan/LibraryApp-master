package controller;

import model.BorrowedBook;
import model.User;
import service.AuthenticationService;
import service.LibraryService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;


public class LibraryApplication {

    //this is our instance of BBS
    static LibraryService libraryService = new LibraryService();
    static AuthenticationService authenticationService = new AuthenticationService();

    static boolean loginUser = false;
    static boolean loginLibrarian = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        menu();
        int choice = scanner.nextInt();
        boolean order = true;

        while (order) {
            switch (choice) {
                case 1: loginUser = loginAsUser();
                break;
                case 2: loginAsAdmin();
                break;
                case 3:
                    boolean addedBook = addBook();
                    if (addedBook) {
                        System.out.println("Book was successfully added");
                    } else {
                        System.out.println("Book cannot be added");
                    }
                    break;
                case 4:
                    if (loginUser) {
                        scanner.nextLine();
                        System.out.println("Search a book ");
                        String searchBook = scanner.nextLine();
                        if (bookExist(searchBook)){
                            boolean bookBorrowed = borrowBook(1L, searchBook);
                            if (bookBorrowed) {
                                System.out.println("Book was successfully borrowed");
                            }
                        } else {
                            System.out.println("Book doesn't exists");
                        }
                    } else {
                        System.out.println("You must login first");
                    }
                    break;
                case 5:
                    if (loginUser) {
                        scanner.nextLine();
                        System.out.println("Please insert your user number");
                        long userId = scanner.nextLong();
                        System.out.println("Please insert book id");
                        long userIdBookLog = scanner.nextLong();

                        renewBook(userId, userIdBookLog);
                    }
                    break;
                case 6:
                    if (loginUser) {
                        scanner.nextLine();
                        System.out.println("Please insert your user number");
                        long userId = scanner.nextLong();
                        System.out.println("Please insert book id");
                        long bookId = scanner.nextLong();
                        returnBorrowedBook(userId, bookId);
                    }
                    break;
                default:
                    System.out.println(" Exiting...");
                    order = false;
                    break;
            }
            if (order) {
                menu();
                System.out.println("Select an option");
                choice = scanner.nextInt();
            }
        }
    }


    public static void menu() {
        System.out.println("Welcome to the Library Application!");
        System.out.println("Please select an option:");
        System.out.println("1. Login as User");
        System.out.println("2. Login as Admin");
        System.out.println("3. Add a book");
        System.out.println("4. Borrow a book");
        System.out.println("5. Renew date borrowed book");
        System.out.println("6. Return a borrowed book");
    }

    public static boolean loginAsUser() {
        boolean isAuthenticated;
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
            isAuthenticated = authenticationService.authenticateUser(username, password);

            if (isAuthenticated) {
                loginUser = true;
                System.out.println("User authentication successful!");
            } else {
                System.out.println("User authentication failed. Invalid username or password.");
            }
        } else {
            System.out.println("Want to register? (yes/no)");
            String createAccount = scanner.nextLine();

            if (createAccount.equalsIgnoreCase("yes")) {
                return registerUser();
            } else {
                System.out.println("You need an account to login.");
                return false;
            }

        }
        return isAuthenticated;
    }

    public static boolean registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your email");
        String email = scanner.nextLine();

        System.out.println("Please enter your username");
        String userName = scanner.nextLine();

        System.out.println("Please enter your password");
        String password = scanner.nextLine();


        if (email.contains("@") && email.contains(".")
                && email.indexOf("@") == email.lastIndexOf("@")
                && email.lastIndexOf("@") < email.lastIndexOf(".")) {
            Optional<User> user = authenticationService.signUpUser(email, userName, password);
            if (user.isPresent()) {
                loginUser = true;
                System.out.println("User successful signUp");
                return true;
            }
        } else {
            System.out.println("INVALID EMAIL!");
            return false;
        }
        return false;
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

    public static boolean borrowBook(long user, String bookName) {
        Optional<BorrowedBook> borrowBook = libraryService.borrowBook(user, bookName);

        if (borrowBook.isPresent()) {
            System.out.println(borrowBook.get().getBook().getName() + " " + borrowBook.get().getBook().getAuthor() + " " + "borrowed to" + " " + borrowBook.get().getUser().getUserName() );
            return true;
        } else {
            System.out.println("Book " + bookName + " doesn't exists");
            return false;
        }
    }

    public static boolean addBook() {
        Scanner scanner = new Scanner(System.in);
        Date date;
        System.out.println("Enter book name");
        String bookName = scanner.nextLine();
        System.out.println("Enter book author");
        String author = scanner.nextLine();
        System.out.println("Enter book ISBN");
        String ISBN = scanner.nextLine();
        if (!ISBN.matches("^[0-9]{3}-[0-9]-[0-9]{2}-[0-9]{6}-[0-9]$")) {
            System.out.println("ISBN not correct. Please insert gain");
            while (true) {
                ISBN = scanner.nextLine();
                if (ISBN.matches("^[0-9]{3}-[0-9]-[0-9]{2}-[0-9]{6}-[0-9]$")) {
                    break;
                } else {
                    System.out.println("ISBN not correct. Please insert again");
                }
            }
        }

        System.out.println("Enter book genre");
        String genre = scanner.nextLine();
        System.out.println("Enter book release date");
        String releaseDate = scanner.nextLine();

        SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");

        try {
            date = format.parse(releaseDate);
        } catch (ParseException e) {
            System.out.println("Error. Cannot add book. Date not correct ! \n" +
                                       "example Date : jan-2015 ");
            return false;
        }

        return libraryService.addBook(bookName, author, ISBN, genre, date);
    }


    public static boolean bookExist(String name) {
        return libraryService.bookExist(name);

    }

    public static void renewBook(long user, Long bookId) {
        boolean isBookRenewed = libraryService.renewBorrowedBook(user, bookId);
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

    public static void returnBorrowedBook(long user, Long bookID) {
        boolean bookToBeRemoved = libraryService.returnBorrowedBook(user, bookID);

        if (bookToBeRemoved) {
            System.out.println("Book successfully removed from your list");
        } else {
            System.out.println("Cannot find book in your list of borrowed books");
        }
    }
}
