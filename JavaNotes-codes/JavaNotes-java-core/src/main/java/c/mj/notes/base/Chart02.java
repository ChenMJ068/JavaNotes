package c.mj.notes.base;

public class Chart02 {
    public static void main(String[] args) {
        Foo goo =  new Goo();
    }

}

class Foo{
    int foo = 10;

    public int getFoo() {
        return foo;
    }

    public void setFoo(int foo) {
        this.foo = foo;
    }
}

class Goo extends Foo{
    {
        setFoo(5);
        System.out.println("======:"+getFoo());
    }
}