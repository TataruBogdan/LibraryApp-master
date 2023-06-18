package model;

import java.util.Date;
import java.util.Objects;

public class Book {
    private static long nextID = 1;

    private long ID;
    private String name;
    private String author;
    private String ISBN;
    private String genre;
    private boolean isAvailable;
    private Date releaseDate;


    public Book(String name, String author, String ISBN, String genre, Date releaseDate) {
        setID();
        setName(name);
        setAuthor(author);
        setISBN(ISBN);
        setGenre(genre);
        this.releaseDate = releaseDate;

    }

    //TODO
    // ID made from ISBN + Specimen Book Number - Check if ok ?
    private void setID() {
        this.ID = nextID++;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public void setAuthor(String author) {
        if (!author.isEmpty()) {
            this.author = author;
        }
    }

    public void setISBN(String ISBN) {
        if (ISBN.matches("^[0-9]{3}-[0-9]-[0-9]{2}-[0-9]{6}-[0-9]$")) {
            this.ISBN = ISBN;
        }
    }

    public void setGenre(String genre) {
        if (!genre.isEmpty()) {
            this.genre = genre;
        }
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, ISBN);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", genre='" + genre + '\'' +
                ", isAvailable=" + isAvailable +
                ", releaseDate=" + releaseDate +
                '}';
    }


}
