import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   private Node left, right;
   private int N;
   private class Node {
      Item item;
      Node next, prev;
      Node(Item _item) { item = _item; next = prev = null; }
   }
   public Deque() {                           // construct an empty deque
      left = null;
      right = null;
      N = 0;
   }
   public boolean isEmpty() {                // is the deque empty?
      return left == null;
   }
   public int size() {                        // return the number of items on the deque
      return N;
   }
   public void addFirst(Item item) {          // add the item to the front
      if (item == null) throw new NullPointerException("input data is null");
      Node add = new Node(item);
      if (isEmpty()) {
         left = right = add;
      }else {
         add.next = left;
         left.prev = add;
         left = add;
      }
   }
   public void addLast(Item item) {           // add the item to the end
      if (item == null) throw new NullPointerException("input data is null");
      Node add = new Node(item);
      if (isEmpty()) {
         left = right = add;
      } else {
         add.prev = right;
         right.next = add;
         right = add;
      }
   }
   public Item removeFirst() {               // remove and return the item from the front
      if (isEmpty()) throw new NoSuchElementException("no item");
      Item ret = left.item;
      left = left.next;
      return ret;
   }
   public Item removeLast() {                // remove and return the item from the end
      if (isEmpty()) throw new NoSuchElementException("no item");
      Item ret = right.item;
      right = right.prev;
      return ret;
   }
   @Override
   public Iterator<Item> iterator() {
      return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item> {
      private int current = left;
      public boolean hasNext() {
         return current != null;
      }
      public void remove() { }
      public Item next() {
         Item ret = current.item;
         current = current.next;
         return ret;
      }
   }
   

   public static void main(String[] args) {
      // TODO Auto-generated method stub

   }

}
