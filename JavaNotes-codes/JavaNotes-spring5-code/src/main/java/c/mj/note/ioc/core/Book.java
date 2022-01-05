package c.mj.note.ioc.core;

/**
 * create class Book.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 15:49:00
 */
public class Book {
    private String name;
    private String author;
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
