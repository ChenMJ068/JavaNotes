package c.mj.notes.reflect;

public class Person implements MyInteface, MyInterface2 {
    private int id;
    private String name;
    private String age;

    public String desc;

    public Person() {

    }

    public Person(int id) {
        this.id = id;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(int id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private static void privateMethod() {
        System.out.println("private method ...");
    }

    protected static void protectedMethod() {
        System.out.println("protected method ...");
    }

    static void defMethod() {
        System.out.println("def method ...");
    }

    public static void staticMethod() {
        System.out.println("static  method ...");
    }

    @Override
    public void interfaceMethod() {
        System.out.println("interface Method ...");
    }

    @Override
    public void interfaceMethod2() {
        System.out.println("interface2 Method ...");
    }
}
