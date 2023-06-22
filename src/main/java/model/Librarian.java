package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Librarian implements Observer {

    private String userName;
    private String ID;
    private String password;

    private List<String> notifications;

    // check also if Librarian is null ?
    public Librarian(String userName, String ID, String password) {

        if (!userName.isEmpty() && !userName.isBlank() && !ID.isEmpty() && !ID.isBlank() && !password.isEmpty() && !password.isBlank()){
           this.userName = userName;
                   this.ID = ID;
                   this.password = password;
        }
        this.notifications = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public void readNotifications() {
        notifications.forEach(System.out::println);
    }

    @Override
    public void notify(String notification) {
        notifications.add(notification);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Librarian librarian = (Librarian) o;
        return Objects.equals(userName, librarian.userName) && Objects.equals(ID, librarian.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, ID);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + userName + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
