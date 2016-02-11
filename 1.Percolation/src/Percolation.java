import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private int lenN;
   private WeightedQuickUnionUF uf, uf2;
   private boolean[][] visited;
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   {
      if (N <= 0) throw new IllegalArgumentException("N is illigal");
      lenN = N;
      visited = new boolean[N][N];
      for (int i = 0; i < N; ++i)
         for (int j = 0; j < N; ++j)
            visited[i][j] = false;
      uf = new WeightedQuickUnionUF(N*N + 2);
      uf2 = new WeightedQuickUnionUF(N*N + 2);
   }
   
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   {
      if (i <= 0 || i > lenN) throw new IndexOutOfBoundsException("row index i out of bounds");
      if (j <= 0 || j > lenN) throw new IndexOutOfBoundsException("col index j out of bounds");
      int y = i-1;
      int x = j-1;
      visited[i-1][j-1] = true;

      if (y == 0)
         uf.union(lenN*y + x, lenN*lenN);

      if (y == lenN-1)
         uf.union(lenN*y + x, lenN*lenN+1);

      if (x > 0   && visited[y][x-1])
         uf.union(lenN*y + x, lenN*y + x-1);
      
      if (x < lenN-1 && visited[y][x+1])
         uf.union(lenN*y + x, lenN*y + x+1);
      
      if (y > 0   && visited[y-1][x])
         uf.union(lenN*y + x, lenN*(y-1) + x);
      
      if (y < lenN-1 && visited[y+1][x])
         uf.union(lenN*y + x, lenN*(y+1) + x);

      if (y == 0)
         uf2.union(lenN*y + x, lenN*lenN);

      if (x > 0   && visited[y][x-1])
         uf2.union(lenN*y + x, lenN*y + x-1);
      
      if (x < lenN-1 && visited[y][x+1])
         uf2.union(lenN*y + x, lenN*y + x+1);
      
      if (y > 0   && visited[y-1][x])
         uf2.union(lenN*y + x, lenN*(y-1) + x);
      
      if (y < lenN-1 && visited[y+1][x])
         uf2.union(lenN*y + x, lenN*(y+1) + x);
      
   }
   
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
      if (i <= 0 || i > lenN) throw new IndexOutOfBoundsException("row index i out of bounds");
      if (j <= 0 || j > lenN) throw new IndexOutOfBoundsException("col index j out of bounds");
      return visited[i-1][j-1];
   }
   
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
      if (i <= 0 || i > lenN) throw new IndexOutOfBoundsException("row index i out of bounds");
      if (j <= 0 || j > lenN) throw new IndexOutOfBoundsException("col index j out of bounds");
      if (!isOpen(i, j)) return false;
      return uf2.connected(lenN*lenN, lenN*(i-1) + (j-1));
   }
   
   public boolean percolates()             // does the system percolate?
   {
      return uf.connected(lenN*lenN, lenN*lenN+1);
   }
   
   public static void main(String[] args) {
      
   }
}
