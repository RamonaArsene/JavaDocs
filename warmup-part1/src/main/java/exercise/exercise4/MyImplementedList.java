package exercise.exercise4;

import java.util.Comparator;
import java.util.Iterator;

/**
 * You should implement from zero a data structure that acts as an ArrayList.
 * We have a default capacity of {@link MyImplementedList#DEFAULT_CAPACITY} elements of type <code>E</code>.
 * We have a {@link MyImplementedList#size} property that stores the number of elements of type <code>E</code> from the data structure.
 * We have an array property, {@link MyImplementedList#elementData}, that keeps the elements from the data structure.
 * We have a {@link MyImplementedList#LOAD_FACTOR} property that specify the maximum accepted load of the data structure.
 * We have a {@link MyImplementedList#INCREASE_SIZE_FACTOR} property to use it when we must increase the capacity of the data structure.
 * We have a {@link MyImplementedList#capacityAfterExtending} property to use it to retain the new capacity after increasing it.
 * <p>
 * Starting with this properties we must implement a data structure that acts ~ as an ArrayList for some objects of type <code>E</code>.
 * <p>
 * TODO if you need to throw some exceptions YOU MUST create them!
 * TODO if you get some warning from the compiler you can use @SuppressWarnings("all") before the method name!
 * TODO if you get this error "usage of api documented as @since 1.6+" you should go to File > Project Structure > Modules and make sure you have the Language level >= 1.6!
 * TODO you should expose as <code>public</code> only the methods that you usually use over a collection!
 * TODO if you need a getter/setter for the properties you must define then, but keep in mind the java concepts!
 * TODO make sure you cover all the possible use-cases for your data structure!
 *
 * @author Cristian.Dumitru
 * @since 7/3/2017.
 */
public class MyImplementedList<E> implements Iterable<E>, Comparator<E>{

    /**
     * The maximum accepted load property of the data structure.
     */
    private static final double LOAD_FACTOR = 0.75d;

    /**
     * The factor for increasing the size of the data structure.
     */
    private static final int INCREASE_SIZE_FACTOR = 2;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * The array buffer into which the elements of the {@link MyImplementedList} are stored.
     */
    private Object[] elementData;

    /**
     * Property to keep the extended capacity.
     * TODO if you choose another way to implement the extending capacity you can define your own properties,
     * TODO but the rest of them must remain as they are.
     */
    private int capacityAfterExtending;

    //TODO a) implement the empty constructor for the your data structure
    public MyImplementedList() {
        //TODO a) HINT - DEFAULT_CAPACITY, capacityAfterExtending and elementData properties
        this.size = 0;
        this.capacityAfterExtending = DEFAULT_CAPACITY;
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    //TODO k) extend the current default capacity, if the number of elements in the data structure is > 75% of it
    //TODO you should name it: void extendCapacity(int capacity) - HINT use capacity, DEFAULT_CAPACITY, LOAD_FACTOR and INCREASE_SIZE_FA
    public void extendCapacity(){
        Object [] elementDataAux = elementData.clone();
        this.capacityAfterExtending = this.capacityAfterExtending * INCREASE_SIZE_FACTOR;
        elementData = new Object[this.capacityAfterExtending];
        System.arraycopy(elementDataAux, 0, elementData, 0, elementDataAux.length);

    }

    //TODO b) create the int size() method that returns the size of the data structure

    public int size(){
//
        return this.size;
    }

    //TODO c) create the boolean add(E e) method that adds at the end of the data structure an element

    public boolean add(E e){

        if ((this.size() / this.capacityAfterExtending) > LOAD_FACTOR){
            this.extendCapacity();
        }

        elementData[this.size++] = e;

        return true;


    }
    //TODO pay attention to the LOAD_FACTOR of the data structure

    //TODO d) create the boolean isEmpty() method that checks if the data structure have elements
    public boolean isEmpty(){
        boolean hasElem = false;
        for(int i = 0; i < 10; i++){
            if(elementData[i] != null){
                hasElem = true;
            }
        }
        return hasElem;
    }

    //TODO e) create the boolean contains(Object o_O) method that checks if the data structure contains the object o_O

    public boolean contains(Object obj){
        boolean contained = false;
        for (int i = 0; i < elementData.length; i++){
            if(elementData[i].equals(obj)){
                contained = true;
                break;
            }
        }
        return contained;
    }

    //TODO f) create the int indexOf(Object o_O) method that returns the position in the data structure of the object o_O
    //TODO if exists, otherwise return -1

    public int indexOf(Object obj){
        for (int i = 0; i < elementData.length; i++){
            if(elementData[i] == obj){
                return i;
            }
        }
        return -1;
    }

    //TODO g) create the int lastIndexOf(Object o_O) method that returns the last position in the data structure of the object o_O
    //TODO if exists, otherwise return -1

    public int lastIndexOf(Object obj){
        for (int i = elementData.length-1; i >= 0; i--){
            if(elementData[i] == obj){
                return i;
            }
        }
        return -1;
    }

    //TODO h) create the E get(int index) method that returns the object from the given index
    //TODO pay attention to the size property

    public E get(int index){

        if(index > this.size)
            System.out.print("Index out of bounds");
        return (E) elementData[index];
    }

    //TODO i) create the E set(int index, E element) method that updates the value of the element from the given index
    //TODO pay attention to the size property

    public E set(int index, E element){
        E elem;
        elementData[index] = element;
        elem = (E) elementData[index];
        return elem;
    }

    //TODO j) create the E remove(int index) method that removes the element from the given index

    public E remove(int index){
        E elem;
        elem = (E) elementData[index];
        for(int i = index + 1; i< elementData.length; i++){
            elementData[i-1] = elementData[i];

        }
        this.size = this.size - 1;
        return elem;
    }

    //TODO l) implement the iterator() method in order to use the foreach statement over your data structure - HINT Iterable interface
    //TODO and implement a custom iterator for your custom data structure - methods boolean hasNext(), Object next() and void remove()


    public Iterator<E> iterator() {

        Iterator<E> it = new Iterator<E>() {

            private int currentIndex = 0;


            public boolean hasNext() {
                return currentIndex < elementData.length && elementData[currentIndex] != null;
            }


            public E next() {
                return (E) elementData[currentIndex++];
            }
//
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    //TODO m) implement a method, that uses a Comparator, for your data structure to sort the elements
    //TODO you should name it: void sort(Comparator<? super E> c)
    //TODO create a custom comparator that compares objects by their "what you want" :D - HINT Comparator interface

    public int compare(E elem1, E elem2){
        return elem1.hashCode()- elem2.hashCode();
    }
    public void sort(Comparator<E> c){

        for (int i = 0; i<this.size; i++){
            for(int j = i+1; j< this.size; j++)
            if(c.compare((E)this.elementData[i], (E)elementData[j]) > 0)
            {
                Object aux;
                aux = this.elementData[i];
                elementData[i] = elementData[j];
                elementData[j]=aux;

            }
        }
    }
}
