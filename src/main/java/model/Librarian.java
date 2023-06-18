package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Librarian implements Observer {

    private String name;
    private String ID;
    private String password;

    private List<String> notifications;

    public Librarian(String name, String ID,String password) {
        if (!name.isEmpty()&&!ID.isEmpty()&&!password.isEmpty()){
           this.name = name;
                   this.ID = ID;
                   this.password = password;
        }
        this.notifications = new ArrayList<>();
    }

    public String getName() {
        return name;
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
        return Objects.equals(name, librarian.name) && Objects.equals(ID, librarian.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ID);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
