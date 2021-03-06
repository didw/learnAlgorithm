import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] arr;
   private int cur, N;
   
   public RandomizedQueue() {                // construct an empty randomized queue
      arr = (Item[]) new Object[4];
      N = 4;
      cur = 0;
   }
   public boolean isEmpty() {                // is the queue empty?
      return cur == 0;
   }
   public int size() {                       // return the number of items on the queue
      return cur;
   }
   private void increaseSize() {
      Item[] new_arr = (Item[]) new Object[N*2];
      for (int i = 0; i < N; ++i)
         new_arr[i] = arr[i];
      arr = new_arr;
      N *= 2;
   }
   public void enqueue(Item item) {          // add the item
      if (item == null) throw new NullPointerException("input is null");
      arr[cur] = item;
      cur++;
      if (cur == N) {
          increaseSize();
      }
   }
   private void exch(Item[] a, int i, int j) {
      Item tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;      
   }
   private void decreaseSize() {
      N /= 2;
      Item[] new_arr = (Item[]) new Object[N];
      for (int i = 0; i < N/2; ++i)
         new_arr[i] = arr[i];
      arr = new_arr;
   }
   public Item dequeue() {                   // remove and return a random item
      if (cur == 0) throw new NoSuchElementException("no item");
      int k = StdRandom.uniform(cur);
      Item ret = arr[k];
      arr[k] = arr[--cur];
      arr[cur] = null;
      if (cur > 4 && cur < N/4) decreaseSize();
      return ret;
   }
   public Item sample() {                    // return (but do not remove) a random item
      if (cur == 0) throw new NoSuchElementException("no item");
      int k = StdRandom.uniform(cur);
      return arr[k]; 
   }
   @Override
   public Iterator<Item> iterator() {
      return new RandomizedQueueIterator();
   }
   private class RandomizedQueueIterator implements Iterator<Item> {
      private int current = cur;
      private Item[] ar;
      RandomizedQueueIterator() {
         ar = (Item[]) new Object[cur];
         for (int i = 0; i < cur; ++i)
            ar[i] = arr[i];
      }
      public boolean hasNext() {
         return current != 0;
      }
      public void remove() {
         throw new UnsupportedOperationException("remove is not supported");
      }
      public Item next() {
         if (!hasNext()) throw new NoSuchElementException("no item");
         int k = StdRandom.uniform(current);
         Item ret = ar[k];
         ar[k] = ar[--current];
         ar[current] = null;
         return ret;
      }
   }

   public static void main(String[] args) {
      RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
      rq.enqueue(1);
      StdOut.print(rq.dequeue());
      for (int i = 1; i < 10; ++i)
         rq.enqueue(i);
      Iterator<Integer> it = rq.iterator();
      while (it.hasNext())
         StdOut.print(it.next());
      StdOut.println();
      for (int i = 1; i < 10; ++i)
         StdOut.print(rq.dequeue()); 
   }

}
