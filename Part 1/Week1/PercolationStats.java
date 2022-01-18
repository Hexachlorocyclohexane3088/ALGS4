import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import edu.princeton.cs.algs4.Stopwatch;

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
      int cnt = 0;
      while (!p.percolates()) {
        int row = StdRandom.uniform(n) + 1;
        int col = StdRandom.uniform(n) + 1;
        if (!p.isOpen(row, col)) {
          p.open(row, col);
          cnt++;
        }
        
      }
      res[i] = ((double) cnt) / N;
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
    StdOut.printf("%-25s\n", "Please input 2 integers");
		int N = StdIn.readInt();
		int T = StdIn.readInt();
		
		Stopwatch wt = new Stopwatch();
		
		PercolationStats ps = new PercolationStats(N, T);
		
		// elapsed CPU time in seconds
		double elapsed = wt.elapsedTime();
		
		StdOut.printf("%-25s= %.15f\n", "elapsed CPU time", elapsed);
		StdOut.printf("%-25s= %.7f\n", "mean", ps.mean());
		StdOut.printf("%-25s= %.17f\n", "stddev", ps.stddev());
		StdOut.printf("%-25s= [%.15f, %.15f]\n", "%95 confidence interval", 
				ps.confidenceLo(), ps.confidenceHi());

   }

}