import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Node first, last;
   private int size;
   
   private class Node {
      Item item;
      Node next;
   }
   public RandomizedQueue() {                // construct an empty randomized queue
      size = 0;
   }
   public boolean isEmpty() {                // is the queue empty?
      return first == null;
   }
   public int size() {                       // return the number of items on the queue
      return size;
   }
   public void enqueue(Item item) {          // add the item
      Node newfirst = new Node();
      newfirst.item = item;
      newfirst.next = null;
      first.next = newfirst;
      first = newfirst;
   }
   public Item dequeue() {                   // remove and return a random item
      StdRandom.uniform(size)
   }
   public Item sample() {                    // return (but do not remove) a random item
      
   }
   @Override
   public Iterator<Item> iterator() {
      // TODO Auto-generated method stub
      return null;
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub

   }

}
