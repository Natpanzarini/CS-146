import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.*;

// Models an N-by-N percolation system.
public class Percolation {

    // Create an N-by-N grid, with all sites blocked.
    //If site is 0, it is blocked.
    //If site is 1, it is open.
    private static int[][] grid;
    private static int topDummy;
    private static int botDummy;
    private static int n;

    //initilalize UF data structs with dummy nodes
    WeightedQuickUnionUF topUnion;

    public Percolation(int N) {
      if(N <= 0){
        throw new java.lang.IllegalArgumentException();
      }

      grid = new int[N][N];

      //To make N usable in rest of code.
      n = N;

      //initilalize each elem in grid to closed (or zero)
       for(int i = 0; i < N; i++){
         for(int j = 0; j < N; j++){
           grid[i][j] = 0;
         }
       }

      //DummyNode to use for connected() later.
      topDummy = 0;
      botDummy = N*N+1;

      //initilalize UF struct
      topUnion = new WeightedQuickUnionUF(N*N+2);

       //connect dummy top node to first row
       for(int i = 0; i < grid.length; i++){
         topUnion.union(arrToInt(0,i),topDummy);
       }

       //connect dummy bot node to last row
       for(int i = 0; i < grid.length; i++){
         topUnion.union(arrToInt(N-1,i),botDummy);
       }
    }

    // Open site (row, col) if it is not open already.
    public void open(int row, int col) {
        if(row > grid.length || row < 0 || col > grid[0].length || col < 0){
          throw new java.lang.IllegalArgumentException();
        }

        //open the square
        if(grid[row][col] != 1){
          grid[row][col] = 1;
        }


        //check above square is open, union if yes.
        if((col - 1) >= 0 && isOpen(row,col - 1)){
          topUnion.union(arrToInt(row,col),arrToInt(row,col - 1));
        }

        //check below if open, union if yes.
        if((col + 1) < n && isOpen(row,col + 1)){
          topUnion.union(arrToInt(row,col),arrToInt(row,col + 1));
        }

        //check to the right if open, union if yes.
        if((row + 1) < n && isOpen(row + 1,col)){
          topUnion.union(arrToInt(row,col),arrToInt(row + 1,col));
        }

        //check to the left if open, union if yes.
        if((row - 1) >= 0 && isOpen(row - 1,col)){
          topUnion.union(arrToInt(row,col),arrToInt(row - 1,col));
        }
    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) {
      if(row > grid.length - 1 || row < 0 || col > grid[0].length - 1 || col < 0){
        throw new java.lang.IllegalArgumentException();
      }
      return grid[row][col] == 1;
    }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
      if(row > grid.length || row < 0 || col > grid[0].length || col < 0){
        throw new java.lang.IllegalArgumentException();
      }

      if(topUnion.connected(arrToInt(row,col),topDummy)){
        return true;
      }

        return false;
    }

    // Number of open sites.
    public int numberOfOpenSites() {
      int openSites = 0;
      for(int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid[i].length; j++){
          if(isOpen(i,j)){
            openSites++;
          }
        }
      }
         return openSites;
    }

    // Does the system percolate?
    public boolean percolates() {
        return topUnion.connected(topDummy,botDummy);
    }

    private static int arrToInt(int i, int j){
      int hash = n * i + 1 + j;
      return hash;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        }
        else {
            StdOut.println("does not percolate");
        }

        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
