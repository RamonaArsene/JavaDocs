package ro.teamnet.zerotohero.reflection;
import java.lang.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {

    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }
    public static LinkedList<String > list = new LinkedList<String>();
    public static Integer num = new Integer(2);
    private static Integer num2 = 2;
    private static int num3 = 9;
    private static String ss = "abc";

    public ClassReflectionDemoMain(Integer i){
        num = i;
        System.out.println("Constructor");
    }
    public ClassReflectionDemoMain(){
        System.out.println("Constructor fara param");
    }
    public static String getSS(){
        return ss;
    }

    public static void calc(){
        int c = 100 + 100;
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // get the class for a String object, and print it
        String s = new String("string");
        System.out.println (s.getClass());
		

        // get the class of an Enum, and print it
        System.out.println (Day.class);
        

        // get the class of a collection, and print it
        list.add("a");
        list.add("b");
        System.out.println (list.getClass());


        // get the class of a primitive type, and print it
        System.out.println (num.getClass());


        // get and print the class for a field of primitive type
        System.out.println (num2.getClass().getDeclaredField("value"));
        

        // get and print the class for a primitive type, using the wrapper class
        System.out.println(num.TYPE);
        

        // get the class for a specified class name
        System.out.println(ClassReflectionDemoMain.class);
        

        // get the superclass of a class, and print it
        Class superclass = ClassReflectionDemoMain.class.getSuperclass();
        System.out.println(superclass);

        // get the superclass of the superclass above, and print it
        System.out.println(superclass.getSuperclass());

        // get and print the declared classes within some other class
        System.out.println(ClassReflectionDemoMain.class.getDeclaredClasses());



        // print the number of constructors of a class
        System.out.println(ClassReflectionDemoMain.class.getDeclaredConstructors().length);

        // get and invoke a public constructor of a class
        Constructor cons =  ClassReflectionDemoMain.class.getDeclaredConstructor();
        System.out.println(cons.newInstance());

        

        // get and print the class of one private field
       ClassReflectionDemoMain clasa = new ClassReflectionDemoMain();
        Field privatf = clasa.getClass().getDeclaredField("num3");
        privatf.setAccessible(true);
        System.out.println("num3: " + privatf.get(num3));

		// set and print the value of one private field for an object
        privatf.set(num3, 6);
        System.out.println("num3: " + privatf.get(num3));

        

        //TODO get and print only the public fields class
        Field [] fields = clasa.getClass().getFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i]);
        }
        

        //TODO get and invoke one public method of a class
        Method met = clasa.getClass().getDeclaredMethod("getSS");
        System.out.println(met.invoke(new ClassReflectionDemoMain()));
        


        //TODO get and invoke one inherited method of a class
        Method meti = clasa.getClass().getMethod("hashCode");

        System.out.println(meti.invoke(new ClassReflectionDemoMain()));
       
		
		//TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        System.out.println(System.currentTimeMillis());
                System.out.println("Classic way:");
        for (int i = 0; i < 10; i++) {
            ClassReflectionDemoMain.calc();
        }
        System.out.println(System.currentTimeMillis());

        System.out.println(System.currentTimeMillis());
        System.out.println("Reflection:");
        System.currentTimeMillis();
		//TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
        for (int i = 0; i < 100; i++) {
            clasa.getClass().getDeclaredMethod("calc").invoke(clasa);
        }
        System.out.println(System.currentTimeMillis());

        //what do you observe?
		
    }
}
