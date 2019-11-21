// Ramanujan.java: Prints the integers <= N (command-line argument) that can be
// expressed as the sum of two distinct cubes.

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Ramanujan {
    // A data type that encapsulates a pair of numbers (i, j)
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first element of the pair
        private int j;          // second element of the pair
        private int sumOfCubes; // i^3 + j^3

        // Construct a pair (i, j).
        Pair(int i, int j){
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Compare this pair to the other by sumOfCubes.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }

        public int getI(){
          return this.i;
        }

        public int getJ(){
          return this.j;
        }

        public int getSumCubes(){
          return this.sumOfCubes;
        }
    }

    public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      int cubeRootN = (int)Math.cbrt(N);

      MinPQ<Pair> minPQ = new MinPQ<>();

      for(int i = 0; i < cubeRootN; i++){
        Pair temp = new Pair(i,i+1);
        minPQ.insert(temp);
      }

      Pair prev = null;
      while(!minPQ.isEmpty()){
        Pair curr = minPQ.delMin();
        if(prev != null && prev.compareTo(curr) == 0 && curr.getSumCubes() <= N){
            StdOut.println(curr.getSumCubes() + " = " + prev.getI() + "^3 + "
            + prev.getJ() + "^3 = " + curr.getI() + "^3 + " + curr.getJ() + "^3");
        }

        if(curr.getJ() < cubeRootN){
          minPQ.insert(new Pair(curr.getI(),curr.getJ() + 1));
        }
        prev = curr;
      }
    }
}
