import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"rawtypes", "unchecked"})

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private int N; //This maintains the number of items in q
    private Item[] q;

    // Construct an empty queue.
    public ResizingArrayRandomQueue(){
        N = 0;
        q = (Item[]) new Object[2]; //initializes array to size 2
    }

    // Is the queue empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // The number of items on the queue.
    public int size(){
      return N;
    }

    // Add item to the queue.
    public void enqueue(Item item){
      if(item == null){
        throw new NullPointerException();
      }

      //If q is at full capacity, resize it to twice its current capacity
      if(size() == q.length){
        resize(q.length * 2);
      }

      //Insert the given item in q at index N
      q[N] = item;

      //increment size of array
      N++;
    }

    // Remove and return a random item from the queue.
    public Item dequeue(){
      if(isEmpty()){
        throw new NoSuchElementException();
      }

      //Save q[r] in item, where r is a random integer from the interval [0, N)
      int r = StdRandom.uniform(N);
      Item temp = q[r];

      //Set q[r] to q[N - 1] and q[N - 1] to null
      q[r] = q[N-1];
      q[N-1] = null;

      //If q is at quarter capacity, resize it to half its current capacity
      if(size() > 0 && size() == q.length/4){
        resize(q.length/2);
      }

      //Decrement N by one
      N--;

      return temp;
    }

    // Return a random item from the queue, but do not remove it.
    public Item sample(){
      int r = StdRandom.uniform(N);
      return q[r];
    }

    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator(){
      return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item>{
      Item[] items;
      int current;
        RandomQueueIterator(){
          current = 0;
          items = (Item[]) new Object[N];

          for(int i = 0; i < items.length; i++){
            items[i] = q[i];
          }
          StdRandom.shuffle(items);
        }

        public boolean hasNext(){
          return current < items.length;
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
          Item temp = items[current];
          current++;
          return temp; //return a random Item from the array
        }
    }

    //GIVEN
    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    //GIVEN
    // Helper method for resizing the underlying array.
    private void resize(int max) {
      Item[] temp = (Item[]) new Object[max];
      if(q.length != 0){
        for (int i = 0; i < N; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
      }
        q = temp;
    }

    //GIVEN
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q =
            new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
