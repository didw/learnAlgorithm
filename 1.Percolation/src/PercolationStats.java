import java.util.Random;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   private double[] thred;
   private int t;
   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
   {
      t = T;
      thred = new double[T];
      for (int i = 0; i < T; ++i) {
         thred[i] = 0;
         Percolation proc = new Percolation(N);
         Random r1 = new Random(0);
         while (!proc.percolates()) {
            int y = r1.nextInt(N-1);
            int x = r1.nextInt(N-1);
            if (!proc.isOpen(y, x)) {
               proc.open(y+1, x+1);
               thred[i] += 1;
            }
         }
         thred[i] /= N*N;
      }
   }
   
   public double mean()                      // sample mean of percolation threshold
   {
      return StdStats.mean(thred);
   }
   
   public double stddev()                    // sample standard deviation of percolation threshold
   {
      return StdStats.stddev(thred);
   }
   
   public double confidenceLo()              // low  endpoint of 95% confidence interval
   {
      return StdStats.mean(thred) - 1.96 * StdStats.stddev(thred)/t;
   }
   
   public double confidenceHi()              // high endpoint of 95% confidence interval
   {
      return StdStats.mean(thred) + 1.96 * StdStats.stddev(thred)/t;      
   }

   public static void main(String[] args)    // test client (described below)
   {
      
   }

}
