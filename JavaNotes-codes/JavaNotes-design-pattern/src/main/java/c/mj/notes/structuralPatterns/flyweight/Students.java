package c.mj.notes.structuralPatterns.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenMJ
 * @version Students.class, v 0.1 2020/4/19 16:46  Exp$
 */
public class Students {
    private static List<Book> books = new ArrayList<>();

    private static Library library;

    private static void studentBorrow(String bookName){
        books.add(library.libToBorrow(bookName));
    }

    public static void main(String[] args) {
        library = Library.getInstance();
        studentBorrow("java编程思想");
        studentBorrow("设计模式之禅");
        studentBorrow("算法导论");

        books.forEach(Book::borrow);

        System.out.println("学生一共借出了"+books.size()+"本书！");
        System.out.println("图书馆实际借出了"+ library.getAllBookSize()+"本书！");
    }
}
