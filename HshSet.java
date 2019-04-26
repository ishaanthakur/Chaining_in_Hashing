import java.util.LinkedList;

/** An instance is a set of elements of type E that is implemented using a hash table 
 * with chaining. Null is allowed in the set.
 * Matches are based on E.equals(E); and method hashCode must be consistently defined.
 * The implementation guarantees that the load factor is at most 1/2, so that
 * all operations take O(1) expected time --amortized over all operations performed. 
 */

public class HshSet<E> {
    private int Initial_Capacity= 10; // Smallest capacity, and initial one
    
    private double Load_Factor= .75; // Highest allowed load factor

    private int numberOfRehashes= 0;  // Number of rehashes performed on this instance
    private LinkedList<E>[] b;        // The array in which the hashed elements are placed.
    private int size= 0;              // The number of entries in the set

    /** Constructor: an empty set with capacity Initial_Capacity. */
    @SuppressWarnings("unchecked")
    public HshSet() {
        b= new LinkedList[Initial_Capacity];
    }
    

    
    /** return the initial capacity. */
    public int initialCapacity() {
        return Initial_Capacity;
    }

    /** = the number of items in this set. */
    public int size() {
        return size; 
    }

    /** = the capacity of this set
        --i.e. size of the array used to hold its elements. */
    public int capacity() {
        return b.length;
    }

    /** = the number of rehashes performed on this set
          since the last operation to set size to 0. */
    public int getRehashes() {
        return numberOfRehashes; 
    }

    /**  = "e is in the set". */
    public boolean contains(E e) {
        int h= Math.abs(e.hashCode() % b.length);
        if (b[h] == null) return false;
        // b[h] is a linked list
        return b[h].contains(e);
    }

    /** Ensure that e is in this set and return value of sentence
        "e was added because it was not yet in the set." */
    public boolean add(E e) {
        int h= Math.abs(e.hashCode() % b.length);
        if (b[h] == null) {
            b[h]= new LinkedList<E>();
        };

        if (b[h].contains(e)) return false;

        b[h].add(e);
        size= size + 1;
        if (size > .75 * b.length) rehash();  // b is too small

        return true;
    }

    /** Remove e from this set (if it is in it) and return value of sentence
        "e was removed because it was in the set".*/
    public boolean remove(E e) {
        int h= Math.abs(e.hashCode() % b.length);
        if (b[h] == null) return false;

        // b[h] is a linked list
        boolean r=  b[h].remove(e);
        if (r) {
            size= size - 1;
            if (size < b.length/8) rehash(); // if load factor is really small
        }
        return r;
    }

    /** Change the size of this set to zero --but do not rehash. */
    public void clear() {
        size= 0;
        for (int i= 0; i != b.length; i= i+1) {
            b[i]= null;
        }
    }

    /** Rehash array b */
    private void rehash( ) {
        LinkedList<E>[] oldb= b;  // copy of array

        // Create a new, empty array. This is used both for increasing and
        // decreasing the capacity; hence the Math.max expression.
        b= new LinkedList[Math.max(3*size, Initial_Capacity)];
        size= 0;

        // Copy elements from oldb to b
        for (LinkedList<E> ll: oldb) 
            if (ll != null) {
                for (E e : ll) {
                    add(e);
                }
            }

        numberOfRehashes= numberOfRehashes + 1;
    }
}
