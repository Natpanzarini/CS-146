import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;

@SuppressWarnings({"rawtypes", "unchecked"})

public class ArrayST<Key, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int N;

    // Create a symbol table with INIT_CAPACITY.
    public ArrayST() {
        keys = (Key[]) new Object[INIT_CAPACITY];
        values = (Value[]) new Object[INIT_CAPACITY];
        N = 0;
    }

    // Create a symbol table with given capacity.
    public ArrayST(int capacity) {
      keys = (Key[]) new Object[capacity];
      values = (Value[]) new Object[capacity];
    }

    // Return the number of key-value pairs in the table.
    public int size() {
      return N;
    }

    // Return true if the table is empty and false otherwise.
    public boolean isEmpty() {
      return N == 0;
    }

    // Return true if the table contains key and false otherwise.
    public boolean contains(Key key) {
      for(int i = 0; i < N; i++){
        if(keys[i].equals(key)){
          return true;
        }
      }
      return false;
    }

    // Return the value associated with key, or null.
    public Value get(Key key) {
      for(int i = 0; i < N; i++){
        if(keys[i].equals(key)){
          return values[i];
        }
      }
      return null;
    }

    // Put the kev-value pair into the table; remove key from table
    // if value is null.
    public void put(Key key, Value value) {
      if(value == null){
        for(int i = 0; i < N; i++){
          if(keys[i].equals(key)){
            keys[i] = null;
          }
        }
      }

      //This will resize array when it becomes full, and resize will also remove null elements in both arrays if the index
      //of both arrays is null.
      if(size() == keys.length){
        resize(keys.length * 2);
        resize(values.length * 2);
      }

      keys[N] = key;
      values[N] = value;
      N++;
    }

    // Remove key (and its value) from table.
    public void delete(Key key) {
      if(key != null){
        for(int i = 0; i < N; i++){
          if(keys[i].equals(key)){
            keys[i] = null;
            values[i] = null;
          }
        }
      }

      if(size() > 0 && size() == keys.length/4){
        resize(keys.length/2);
        resize(values.length/2);
      }

      N--;
    }

    // Return all the keys in the table.
    public Iterable<Key> keys()  {
      ArrayList<Key> keyList = new ArrayList<>();
      for(int i = 0; i < keys.length; i++){
        if(keys[i] != null){
          keyList.add(keys[i]);
        }
      }
      return keyList;
    }

    // Resize the internal arrays to capacity.
    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
          if(keys[i] != null && values[i] != null){ //added this line to remove null elements from array.
            tempk[i] = keys[i];
            tempv[i] = values[i];
          }
        }
        values = tempv;
        keys = tempk;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();
        int count = 0;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            st.put(s, ++count);
        }
        for (String s : args) {
            st.delete(s);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
