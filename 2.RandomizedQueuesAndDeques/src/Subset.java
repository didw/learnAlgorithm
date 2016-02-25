import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

   public static void main(String[] args) {
      RandomizedQueue<String> rq = new RandomizedQueue<String>();
      int k = StdIn.readInt();
      while (!StdIn.isEmpty()) {
         String a = StdIn.readString();
         if (a == null) break;
         rq.enqueue(a);
      }
      for (int i = 0; i < k; ++i) {
         StdOut.println(rq.dequeue());
      }
   }
}
