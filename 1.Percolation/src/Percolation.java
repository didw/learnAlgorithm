import java.util.Random;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private static int n;
   private WeightedQuickUnionUF uf;
   private boolean[][] perc;
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   {
      n = N;
      perc = new boolean[N][N];
      for (int i = 0; i < N; ++i)
         for (int j = 0; j < N; ++j)
            perc[i][j] = false;
      
      uf = new WeightedQuickUnionUF(N*N);
      for (int i = 1; i < N; ++i) {
         uf.union(0, i);
         uf.union(N*(N - 1), N*(N - 1) + i);
      }
   }
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   {
      if (i <= 0 || i > n) throw new IndexOutOfBoundsException("row index i out of bounds");
      if (j <= 0 || j > n) throw new IndexOutOfBoundsException("col index i out of bounds");
      int y = i-1;
      int x = j-1;
      if (x > 0 && perc[y][x-1])
         uf.union(n*y+x, n*y+x-1);
      if (x < n-1 && perc[y][x+1])
         uf.union(n*y+x, n*y+x+1);
      if (y > 0 && perc[y-1][x])
         uf.union(n*y+x, n*(y-1)+x);
      if (y < n-1 && perc[y+1][x])
         uf.union(n*y+x, n*(y+1)+x);
      perc[y][x] = true;
   }
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
      if (i <= 0 || i > n) throw new IndexOutOfBoundsException("row index i out of bounds");
      if (j <= 0 || j > n) throw new IndexOutOfBoundsException("row index i out of bounds");
      return perc[i-1][j-1];
   }
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
      if (i <= 0 || i > n) throw new IndexOutOfBoundsException("row index i out of bounds");
      if (j <= 0 || j > n) throw new IndexOutOfBoundsException("row index i out of bounds");
      if (!isOpen(i,j)) return false;
      return uf.connected(0, n*(i-1) + (j-1));
   }
   public boolean percolates()             // does the system percolate?
   {
      if (n == 1) return perc[n-1][n-1];
      return uf.connected(0, n*(n-1));
   }

   public static void main(String[] args)  // test client (optional)
   {
      Percolation p = new Percolation(10);
      Random r1 = new Random(0);
      while (!p.percolates()) {
         int i = r1.nextInt(n-1);
         int j = r1.nextInt(n-1);
         p.open(i+1, j+1);
         System.out.println(i+1);
         System.out.println(j+1);
      }
   }
}
