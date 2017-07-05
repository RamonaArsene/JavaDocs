package exercise.exercise1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 1: Compute the sum, the minimum and the maximum element from a given list (givenList) using three
 *             different ways to iterate over a List:
 *             a) ListIterator (implement it in the iterateUsingListIterator() method)
 *             b) for loop (implement it in the iterateUsingForLoop() method)
 *             c) foreach loop (implement it in the iterateUsingForEachLoop() method)
 *
 *             In order to test your implementations you need to run the Exercise1Test from the test/java package
 *             (right-click on Exercise1Test class then click Run 'Exercise1Test' )
 */
public class Exercise1{

    // The list that contains some Integer values
    private List<Integer> givenList;

    public Exercise1(List<Integer> l) {
       this.givenList = l;
    }

    // TODO Exercise #1 a) Compute sum and get the min and the max from givenList, iterating through it using ListIterator
    public List<Integer> iterateUsingListIterator(){

        // This List is used only for testing so you don't have to modify it
        List<Integer> testValues = new ArrayList<Integer>();


        // TODO Exercise #1 a1) In order to pass the tests, you need to name your variables sum, min and max or if

        int sum = 0;
        int max = 0;
        int min = this.givenList.get(0);
        Iterator<Integer> it = this.givenList.iterator();
        while(it.hasNext()){
            Integer num;
            num = it.next();
            sum = sum + num;
            if(num > max){
                max = num;
            }
            if(num < min){
                min = num;
            }
        }
        // TODO Exercise #1 a1) you want to name them differently you need to modify them when you add them to testValues



        // TODO Exercise #1 a2) Uncomment the following three lines in order to check your computed values using tests
        testValues.add(sum);
        testValues.add(min);
        testValues.add(max);

        return testValues;
    }

    // TODO Exercise #1 b) Compute the sum and get the min and the max from the even (RO: pare) positions in the list,
    // TODO Exercise #1 b) iterating through it using classic for loop
    public List<Integer> iterateUsingForLoop(){

        // This List is used only for testing so you don't need to modify it
        List<Integer> testValues = new ArrayList<Integer>();

        int sum = 0,
            min = givenList.get(0),
            max = 0;

        for (int i = 0; i < givenList.size(); i = i+2){
            sum = sum + givenList.get(i);
            if (givenList.get(i) > max){
                max = givenList.get(i);
            }
            if(givenList.get(i) < min){
                min = givenList.get(i);
            }
        }


        // TODO Exercise #1 b1) In order to pass the tests, you need to name your variables sum, min and max or if
        // TODO Exercise #1 b1) you want to name them differently you need to modify them when you add them to testValues



        // TODO Exercise #1 b2) Uncomment the following three lines in order to check your computed values using tests
        testValues.add(sum);
        testValues.add(min);
        testValues.add(max);

        return testValues;
    }

    // TODO Exercise #1 c) Compute the sum and get the min and the max from the odd (RO: impare) elements of the list
    // TODO Exercise #1 c) iterating through it using foreach loop
    public List<Integer> iterateUsingForEachLoop(){

        // This List is used only for testing so you don't need to modify it
        List<Integer> testValues = new ArrayList<Integer>();

            int sum = 0,
                min = givenList.get(0),
                max = 0;


        for(Integer it : givenList){

            if(it % 2 == 1){
                sum = sum + it;
                if (it > max){
                    max = it;
                }
                if(it < min){
                    min = it;
                }
            }
        }


        // TODO Exercise #1 c1) In order to pass the tests, you need to name your variables sum, min and max or if
        // TODO Exercise #1 c1) you want to name them differently you need to modify them when you add them to testValues



        // TODO Exercise #1 c2) Uncomment the following three lines in order to check your computed values using tests
        testValues.add(sum);
        testValues.add(min);
        testValues.add(max);

        return testValues;
    }
}
