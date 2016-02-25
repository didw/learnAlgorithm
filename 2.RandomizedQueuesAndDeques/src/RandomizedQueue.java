import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] arr;
   private int cur, size, N;
   
   public RandomizedQueue() {                // construct an empty randomized queue
      arr = new int[4];
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
      Item[] new_arr = new Item[N*2];
      for (int i = 0; i < N; ++i)
         new_arr[i] = arr[i];
      arr = new_arr;
      N *= 2;
   }
   public void enqueue(Item item) {          // add the item
      arr[cur] = (Item)new Object(item);
      cur++;
      if (cur==N) {
          increaseSize();
      }
      int k = StdRandom.uniform(cur);
      swap(arr, k, cur);
   }
   private decreaseSize() {
      N /= 2;
      Item[] new_arr = new Item[N];
      for (int i = 0; i < N/2; --i)
         new_arr[i] = arr[i];
      arr = new_arr;
   }
   public Item dequeue() {                   // remove and return a random item
      if (cur == 0) return null;
      Item ret = arr[--cur];
      if (cur > 4 && cur < N/4) decreaseSize();
      return ret;
   }
   public Item sample() {                    // return (but do not remove) a random item
      int k = StdRandom.uniform(cur);
      return arr[k]; 
   }
   @Override
   public Iterator<Item> iterator() {
      return new RandomizedQueueIterator();
   }
   private class RandomizedQueueIterator implements Iterator<Item> {
      private int current = 0;
      public boolean hasNext() {
         return current != cur;
      }
      public void remove() { }
      public Item next() {
         return arr[current++];
      }
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub

   }

}
