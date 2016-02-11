import java.util.Random;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   private double[] thred;
   private int t;
   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
   {
      if (N <= 0 || T <= 0) throw new IllegalArgumentException("N or T is illegal");
      t = T;
      thred = new double[T];
      Random r1 = new Random();
      for (int i = 0; i < T; ++i) {
         thred[i] = 0;
         Percolation proc = new Percolation(N);
         while (!proc.percolates()) {
            int y = r1.nextInt(N);
            int x = r1.nextInt(N);
            if (!proc.isOpen(y+1, x+1)) {
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
      return StdStats.mean(thred) - 1.96 * StdStats.stddev(thred)/Math.sqrt(t);
   }
   
   public double confidenceHi()              // high endpoint of 95% confidence interval
   {
      return StdStats.mean(thred) + 1.96 * StdStats.stddev(thred)/Math.sqrt(t);      
   }


   public static void main(String[] args) {
      PercolationStats pes = new PercolationStats(100, 10);
      System.out.println(pes.mean());
      System.out.println(pes.stddev());
      System.out.println(pes.confidenceLo());
      System.out.println(pes.confidenceHi());
   }
}
