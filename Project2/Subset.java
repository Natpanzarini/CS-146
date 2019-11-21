import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Takes a command-line integer k; reads in a sequence of strings from
// standard input; and prints out exactly k of them, uniformly at random.
// Note that each item from the sequence is printed out at most once.
public class Subset {
    public static void main(String[] args) {
      ResizingArrayRandomQueue<String> q = new ResizingArrayRandomQueue<>();
      Integer k = Integer.parseInt(args[0]);
        while(!StdIn.isEmpty()){
          int counter = 0;
          String string = StdIn.readString();
          if(counter < k){
            q.enqueue(string);
            counter++;
          }
          else if(StdRandom.uniform(k) == 1){
            q.dequeue();
            q.enqueue(string);
          }
        }

        for(int i = 0; i < k; i++){
          System.out.println(q.dequeue());
        }
    }
}
