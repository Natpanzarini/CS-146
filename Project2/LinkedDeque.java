import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    // Helper doubly-linked list class.
    private class Node{
        private Item item;
        private Node next;
        private Node prev;
    }

    // Construct an empty deque.
    public LinkedDeque() {
      //initialize head/tail to single node for new deque.
      this.head = null;
      this.tail = null;
      size = 0;
    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // The number of items on the deque.
    public int size() {
      return size;
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
      if(item == null){
        throw new NullPointerException("Item is null");
      }

      //create new node to add, and temp to store old head
      Node newNode = new Node();
      newNode.item = item;

      if(this.isEmpty()){
        head = newNode;
        tail = newNode;
      }
      else{
        //connect new node to current head
        newNode.next = head;

        //assign old head.prev to new head
        head.prev = newNode;

        //connect newNode.prev to null because it is the first node.
        newNode.prev = null;

        //reassign head to newnode that's at the beginning.
        head = newNode;
      }
      size++;
    }

    // Add item to the end of the deque.
    public void addLast(Item item){
      if(item == null){
        throw new NullPointerException("Item is null");
      }

      //create node to add, and temp to store old tail
      Node newNode = new Node();
      newNode.item = item;

      if(this.isEmpty()){
        head = newNode;
        tail = newNode;
      }
      else{
        //connect newNode to the current tail
        tail.next = newNode;

        //connect new tail to old tail
        newNode.prev = tail;

        //reassign tail to new tail
        tail = newNode;

        //point new node to null, as it is the last node now.
        tail.next = null;
      }
      size++;
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst(){
      if(this.isEmpty()){
        throw new NoSuchElementException("Deque is empty");
      }

      //store head in temp node to return item at the end
      Node temp = head;

      if(this.size() == 1){
        head = null;
        tail = null;
      }
      else{
        //reassign head
        head = head.next;

        //change new firstnode.prev to null
        head.prev = null;
      }

      size--;
      return temp.item;
    }

    // Remove and return item from the end of the deque.
    public Item removeLast(){
      if(this.isEmpty()){
        throw new NoSuchElementException("Deque is empty");
      }

      //store tail in temp node to return item at the end
      Node temp = tail;

      if(this.size() == 1){
        head = null;
        tail = null;
      }
      else{
        //reassign tail
        tail = tail.prev;

        //remove pointer to last node
        tail.next = null;
      }

      size--;
      return temp.item;
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
      DequeIterator iter = new DequeIterator();
      return iter;
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        //Use a node object to keep track of iteration through deque
        private Node iter;

        DequeIterator() {
          iter = head;
        }

        public boolean hasNext(){
          return iter.next != null;
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next(){
          if(iter.next == null){
            throw new NoSuchElementException("No next item");
          }

          //advance iter to next node
          iter = iter.next;

          return iter.item;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
            + "several powers, having been originally breathed into a few "
            + "forms or into one; and that, whilst this planet has gone "
            + "cycling on according to the fixed law of gravity, from so "
            + "simple a beginning endless forms most beautiful and most "
            + "wonderful have been, and are being, evolved. ~ "
            + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            }
            else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
