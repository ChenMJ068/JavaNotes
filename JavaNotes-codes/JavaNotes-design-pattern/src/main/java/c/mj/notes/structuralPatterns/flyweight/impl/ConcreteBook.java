package c.mj.notes.structuralPatterns.flyweight.impl;

import c.mj.notes.structuralPatterns.flyweight.Book;

/**
 * @author ChenMJ
 * @version ConcreteBook.class, v 0.1 2020/4/19 16:37  Exp$
 */
public class ConcreteBook implements Book {
    private String name;

    public ConcreteBook(String name) {
        this.name = name;
    }

    @Override
    public void borrow() {
        System.out.println("图书馆借出一本书，书名:" + this.name);
    }
}
