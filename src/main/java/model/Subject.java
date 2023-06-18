package model;

public interface Subject {

    void attachLibrarian(Observer observer);
    void detachLibrarian(Observer observer);
    void notifyLibrarians(String notification);
}
