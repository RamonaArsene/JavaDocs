package exercise.exercise0;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it

        ArrayList<String> list0a = new ArrayList<String>();
        for (int i = 0; i < 10; i++){
            list0a.add(new String("Elementul " + i));
        }

        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)

        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements
        Iterator <String> it= list0a.iterator();
        while (it.hasNext()){
            System.out.print(it.next());
        }

        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        for (int i = 0; i < list0a.size(); i++){
            System.out.println(list0a.get(i));
        }

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements

        for (String  s : list0a){
            System.out.println(s);

        }

    }

    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
    }
}
