package c.mj.notes.structuralPatterns.flyweight;

import c.mj.notes.structuralPatterns.flyweight.impl.ConcreteBook;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenMJ
 * @version Llibrary.class, v 0.1 2020/4/19 16:39  Exp$
 */
public class Library {
    private Map<String, Book> bookPools = new HashMap<>();

    private static Library library = new Library();

    public static Library getInstance() {
        return library;
    }

    public Book libToBorrow(String bookName) {
        Book book = null;
        if (bookPools.containsKey(bookName)) {
            book = bookPools.get(bookName);
        } else {
            book = new ConcreteBook(bookName);
            bookPools.put(bookName, book);
        }
        return book;
    }

    public int getAllBookSize() {
        return bookPools.size();
    }
}
