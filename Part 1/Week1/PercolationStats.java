import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class PercolationStats {
  private double[] res;
  private double mean;
  private double stddev;
    // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
    int N = n * n;
    res = new double[trials];
	  for (int i = 0; i < trials; i++) {
      Percolation p = new Percolation(n); 
      while (!p.percolates()) {
        int row = StdRandom.uniform(1,n);
        int col = StdRandom.uniform(1,n);
        p.open(row, col);
      }
      res[i] = (double)p.numberOfOpenSites() / N;
	  }
    mean = StdStats.mean(res);
    stddev = StdStats.stddev(res);
	}

    // sample mean of percolation threshold
    public double mean() {
      return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
      return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
      return mean - 1.96 * stddev / Math.sqrt(res.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
      return mean + 1.96 * stddev / Math.sqrt(res.length);
    }

   // test client (see below)
   public static void main(String[] args) {

   }

}