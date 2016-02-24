import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   private Item[] arr;
   private int left, right, N;
   public Deque() {                           // construct an empty deque
      arr = (Item[])new Object[4];
      left = 2;
      right = 2;
      N = 4;
   }
   public boolean isEmpty() {                // is the deque empty?
      return left == right;
   }
   public int size() {                        // return the number of items on the deque
      return right - left;
   }
   private void stretchLeft() {
      Item[] arr_new = (Item[])new Object[N*2];
      for (int i = 0; i < N; ++i) {
         arr_new[i+N] = arr[i];
      }
      arr = arr_new;
      right += N;
      left += N;
      N *= 2;
   }
   public void addFirst(Item item) {          // add the item to the front
      if (item == null) throw new NullPointerException("input data is null");
      arr[left--] = item;
      if (left == 0) stretchLeft();
   }
   private void stretchRight() {
      Item[] arr_new = (Item[])new Object[N*2];
      for (int i = 0; i < N; ++i)
         arr_new[i] = arr[i];
      arr = arr_new;
      N *= 2;
   }
   public void addLast(Item item) {           // add the item to the end
      if (item == null) throw new NullPointerException("input data is null");
      arr[right++] = item;
      if (right == N-1) stretchRight();
   }
   private void shortenLeft() {
      N /= 2;
      Item[] arr_new = (Item[])new Object[N];
      for (int i = 0; i < N; ++i) {
         arr_new[i] = arr[i+N];
      }
      arr = arr_new;
      left -= N;
      right -= N;
   }
   public Item removeFirst() {               // remove and return the item from the front
      if (isEmpty()) throw new NoSuchElementException("no item");
      Item ni = arr[left++];
      if (N > 4 && left > N*3/4) shortenLeft();
      return ni;
   }
   private void shortenRight() {
      N /= 2;
      Item[] arr_new = (Item[])new Object[N];
      for (int i = 0; i < N; ++i) {
         arr_new[i] = arr[i];
      }
      arr = arr_new;
   }
   public Item removeLast() {                // remove and return the item from the end
      if (isEmpty()) throw new NoSuchElementException("no item");
      if (isEmpty()) return null;
      Item ni = arr[right--];
      if (N>4 && right < N/4) shortenRight();
      return ni;
   }
   @Override
   public Iterator<Item> iterator() {
      return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item> {
      private int current = left;
      public boolean hasNext() {
         return current != right;
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
