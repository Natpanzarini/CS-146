// FrequencyCounter.java: Reads in a command-line integer and sequence of words
// from standard input and prints out all the words (whose length exceeds the
// threshold) that occur most frequently to standard output. It also prints out
// the number of words whose length exceeds the threshold and the number of
// distinct such words.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;

public class FrequencyCounter {
    public static void main(String[] args) {
      Integer threshold = Integer.parseInt(args[0]);
      ArrayST<String, Integer> haveSeen = new ArrayST<String, Integer>();
      int words = 0; //number of words whose length exceeds threshold.
      int distinct = 0; //number of distinct words

      while(!StdIn.isEmpty()){
        String s = StdIn.readString();
        if(s.length() > threshold){
          words++;
        }

        if(haveSeen.contains(s)){
          System.out.println("test");
          haveSeen.put(s,haveSeen.get(s) + 1);
        }
        else{
          distinct++;
          haveSeen.put(s,1);
        }
      }

        int max = 0;

        for(String s: haveSeen.keys()){
          if(haveSeen.get(s) > max){
            max = haveSeen.get(s);
          }
        }
        System.out.println(max);
        for(String s: haveSeen.keys()){
          if(haveSeen.get(s) == max){
            System.out.print(s + " ");
          }
        }
        System.out.println(max);
        System.out.println("distinct = " + distinct);
        System.out.println("words = " + words);

    }
}
