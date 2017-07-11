package exercise5;

import exercise4.MyHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.LinkedList;
import  java.util.HashSet;

/**
 * Create a HashMap that uses to store the buckets your implementation of MyImplementedList that you
 * created in the Collections I workshop.
 *
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyHashMapWithMyImplementedList {

    // TODO uncomment the following line and add your MyImplementedList implementation to the project
   private MyImplementedList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;
    public int size = 0;


    public MyHashMapWithMyImplementedList() {
        buckets = new MyImplementedList<LinkedList<MyEntry>>(16);
        for (int i = 0; i< BUCKET_ARRAY_SIZE; i++ ) {
            buckets.add(new LinkedList<MyEntry>());
        }
    }

    public String get(String key) {

        if(key == null) return null;
        if(buckets.get(Math.abs(key.hashCode()%(BUCKET_ARRAY_SIZE-1))).isEmpty()) return null;

        LinkedList<MyEntry> res = buckets.get(Math.abs(key.hashCode()%(BUCKET_ARRAY_SIZE-1)));
        for (MyEntry re : res) {
            if(re.getKey().equals(key))
                return re.getValue();
        }
        return null;
    }

    public void put(String key, String value) {
        if(key == null){
            if (!this.containsKey(key)) {
                buckets.get(0).add(new MyEntry(key, value));
                this.size++;
            } else {
                for (MyEntry myEntry : buckets.get(0)) {
                    if (myEntry.getKey()==null) {
                        myEntry.setValue(value);
                    }
                }
            }
        }
        else {
            if (!this.containsKey(key)) {
                buckets.get(Math.abs(key.hashCode() % (BUCKET_ARRAY_SIZE - 1))).add(new MyEntry(key, value));
                this.size++;
            } else {
                for (MyEntry myEntry : buckets.get(Math.abs(key.hashCode() % (BUCKET_ARRAY_SIZE - 1)))) {
                    if (myEntry.getKey().equals(key)) {
                        myEntry.setValue(value);
                    }
                }
            }
        }

    }

    public Set<String> keySet() {
        Set<String> res = new HashSet<String>();


        for (LinkedList<MyEntry> bucket : buckets) {
            if (!bucket.isEmpty()) {
                for (MyEntry myEntry : bucket)
                    res.add(myEntry.getKey());

            }

        }
        return res;
    }

    public Collection<String> values() {
        // TODO
        Set<String> res = new HashSet<String>();


        for (LinkedList<MyEntry> bucket : buckets) {
            if (!bucket.isEmpty()) {
                for (MyEntry myEntry : bucket) {
                    res.add(myEntry.getValue());
                }
            }
        }
        return res;
}

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        String valDel = null;
        for (MyEntry myEntry : buckets.get(Math.abs(key.hashCode() % (BUCKET_ARRAY_SIZE - 1)))) {
            if(key == null){
                if(myEntry.getKey()==null){
                    valDel = myEntry.getValue();
                    buckets.get(0).remove(myEntry);
                    this.size--;
                    break;
                }

            }
            if (myEntry.getKey().equals(key)) {
                valDel = myEntry.getValue();
                buckets.get(Math.abs(key.hashCode() % (BUCKET_ARRAY_SIZE - 1))).remove(myEntry);
                this.size--;
                break;
            }
        }
        //buckets.get(Math.abs(key.hashCode() % (BUCKET_ARRAY_SIZE - 1))).remove(new MyEntry(key, valDel));


        return valDel;
    }

    public boolean containsKey(String key) {
        // TODO
        for (LinkedList<MyEntry> bucket : buckets) {
            if (!bucket.isEmpty()) {
                for (MyEntry myEntry : bucket) {
                    if(key == null && myEntry.getKey()==null)
                        return true;
                    if (myEntry.getKey().equals(key)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean containsValue(String value) {
        // TODO
        for (LinkedList<MyEntry> bucket : buckets) {
            if (!bucket.isEmpty()) {
                for (MyEntry myEntry : bucket) {
                    if (myEntry.getValue().equals(value)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        return this.size;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
        buckets = new MyImplementedList<LinkedList<MyEntry>>(16);
        for (int i = 0; i< BUCKET_ARRAY_SIZE; i++ ) {
            buckets.add(new LinkedList<MyEntry>());
        }
        this.size = 0;
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        Set<MyEntry> set = new HashSet<MyEntry>();

        for (LinkedList<MyEntry> bucket : buckets) {
            for (MyEntry myEntry : bucket) {
                set.add(myEntry);
            }

        }

        return null;
    }

    public boolean isEmpty() {
        // TODO
        return this.size() == 0;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
