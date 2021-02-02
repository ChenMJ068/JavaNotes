package c.mj.notes.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
       //demo1();
        //demo2();
        //demo3();
        //demo4();
        //demo5();
        demo6();
    }




    public static void demo6() throws IllegalAccessException, InstantiationException {
        Class<?> perClazz = null;
        try {
            perClazz = Class.forName("c.mj.notes.reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获取当前反射所代表类（接口）的对象（实例）
        Object instance = perClazz.newInstance();
        Person person = (Person) instance;
        person.interfaceMethod();

    }

    public static void demo5(){
        Class<?> perclazz = null;
        try {
            perclazz = Class.forName("c.mj.notes.reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor<?>[] constructors = perclazz.getConstructors();
        for (Constructor<?> con: constructors) {
            System.out.println(con);
        }

        System.out.println("==============");

        Field[] fields = perclazz.getFields();
        for (Field field: fields) {
            System.out.println(field);
        }

        System.out.println("==============");
        Field[] declaredFields = perclazz.getDeclaredFields();
        for(Field field: declaredFields){
            System.out.println(field);
        }
    }

    public static void demo4(){
        Class<?> perclazz = null;
        try {
            perclazz = Class.forName("c.mj.notes.reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<?> superClass = perclazz.getSuperclass();
        System.out.println(superClass);
    }

    public static void demo3(){
        Class<?> perClazz = null;
        try{
            perClazz = Class.forName("c.mj.notes.reflect.Person");
        }catch (Exception e){
            e.printStackTrace();
        }
        Class<?>[] clazzs = perClazz.getInterfaces();
        for (Class<?> clazz :clazzs ) {
            System.out.println(clazz);
        }
    }

    public static void demo2(){
        Class<?> perClazz = null;
        try{
             perClazz = Class.forName("c.mj.notes.reflect.Person");
        }catch (Exception e){
            e.printStackTrace();
        }

        Method[] methods = perClazz.getMethods();
        for (Method method :methods){
            System.out.println(method);
        }


        System.out.println("==========================================");
        methods = perClazz.getDeclaredMethods();
        for (Method method :methods){
            System.out.println(method);
        }
    }

    public static void demo1(){
        System.out.println("==1===================");
        try{
            Class<?> perClazz1 = Class.forName("c.mj.notes.reflect.Person");
            System.out.println(perClazz1);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("==2===================");
        Class<?> perClazz2 = Person.class;
        System.out.println(perClazz2);

        System.out.println("==3===================");
        Person per = new Person();
        System.out.println(per.getClass());
    }
}
