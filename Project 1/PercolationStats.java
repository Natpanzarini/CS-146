import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
  private int N;
  private int T;
  private double[] results;
  private double truePT;
  private Percolation grid;

    // Perform T independent experiments (Monte Carlo simulations) on an
    // N-by-N grid.
    public PercolationStats(int N, int T) {
      if(N <= 0 || T <= 0){
        throw new IllegalArgumentException("Invalid input, please specify numbers greater than 0.");
      }

      this.N = N;
      this.T = T;
      results = new double[T];

      for(int i = 0; i < T; i++){
        grid = new Percolation(N);
        int openedSites = 0;

        while(!grid.percolates()){
          int row = StdRandom.uniform(0,N);
          int col = StdRandom.uniform(0,N);

          if(!grid.isOpen(row,col)){
            grid.open(row,col);
            openedSites++;
          }
        }
        double percThreshold = (grid.numberOfOpenSites()/(double)(N*N));
        results[i] = percThreshold;
      }

    }

    // Sample mean of percolation threshold.
    public double mean() {
      return StdStats.mean(results);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
      return StdStats.stddev(results);
    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
      return mean() - ((1.96*stddev())/Math.sqrt(T));
    }

    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
      return mean() + ((1.96*stddev())/Math.sqrt(T));
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
