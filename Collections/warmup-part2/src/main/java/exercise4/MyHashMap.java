package exercise4;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;
    public int size = 0;
    public MyHashMap() {

        buckets = new ArrayList<LinkedList<MyEntry>>(16);
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


        return size;
    }

    public void clear() {
        buckets = new ArrayList<LinkedList<MyEntry>>(16);
        for (int i = 0; i< BUCKET_ARRAY_SIZE; i++ ) {
            buckets.add(new LinkedList<MyEntry>());
        }
        this.size = 0;
    }

    public Set<MyEntry> entrySet() {
        Set<MyEntry> set = new HashSet<MyEntry>();

        for (LinkedList<MyEntry> bucket : buckets) {
           set.addAll(bucket);
        }

        return set;
    }

    public boolean isEmpty() {
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
