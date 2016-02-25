import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
   private Node left, right;
   private int N;
   private class Node {
      private Item item;
      private Node next, prev;
      Node(Item itemIn) { 
         item = itemIn; 
         next = null; 
         prev = null; 
       }
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
         left = add;
         right = add;
      } else {
         add.next = left;
         left.prev = add;
         left = add;
      }
      N++;
   }
   public void addLast(Item item) {           // add the item to the end
      if (item == null) throw new NullPointerException("input data is null");
      Node add = new Node(item);
      if (isEmpty()) {
         left = add;
         right = add;
      } else {
         add.prev = right;
         right.next = add;
         right = add;
      }
      N++;
   }
   public Item removeFirst() {               // remove and return the item from the front
      if (isEmpty()) throw new NoSuchElementException("no item");
      Item ret = left.item;
      left = left.next;
      if (left == null)
         right = null;
      else
         left.prev = null;
      N--;
      return ret;
   }
   public Item removeLast() {                // remove and return the item from the end
      if (isEmpty()) throw new NoSuchElementException("no item");
      Item ret = right.item;
      right = right.prev;
      if (right == null)
         left = null;
      else
         right.next = null;
      N--;
      return ret;
   }
   @Override
   public Iterator<Item> iterator() {
      return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item> {
      private Node current = left;
      public boolean hasNext() {
         return current != null;
      }
      public void remove() {
         throw new UnsupportedOperationException("remove is not supported");
      }
      public Item next() {
         if (!hasNext())
            throw new NoSuchElementException("no item");
         Item ret = current.item;
         current = current.next;
         return ret;
      }
   }
   

   public static void main(String[] args) {
      Deque<Integer> dq = new Deque<Integer>();
      StdOut.println(dq.isEmpty());
      dq.addFirst(1);
      dq.addFirst(2);
      StdOut.println(dq.removeFirst());
      StdOut.println(dq.removeFirst());
   }
}
