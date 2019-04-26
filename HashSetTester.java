import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class HashSetTester {

    @Test
    public void testConstructorsAndInitialization() {
        HshSet<String> hs= new HshSet<String>();
        assertEquals(0, hs.size());
        assertEquals(hs.initialCapacity(), hs.capacity());
        assertEquals(0, hs.getRehashes());
    }
    
    @Test
    public void testSize1and2Set() {
        HshSet<String> hs= new HshSet<String>();
        String ss= "abc";
        hs.add(ss); 
        assertEquals(1, hs.size());
        assertEquals(hs.initialCapacity(), hs.capacity());
        assertEquals(0, hs.getRehashes());
        assertEquals(true, hs.contains("abc"));
        assertEquals(false, hs.contains("abcd"));
        
        hs.add(ss);  
        assertEquals(1, hs.size());
        assertEquals(hs.initialCapacity(), hs.capacity());
        assertEquals(0, hs.getRehashes());
        assertEquals(true, hs.contains("abc"));
        assertEquals(false, hs.contains("abcd"));
        
        hs.add("abcd");  
        assertEquals(2, hs.size());
        assertEquals(hs.initialCapacity(), hs.capacity());
        assertEquals(0, hs.getRehashes());
        assertEquals(true, hs.contains("abc"));
        assertEquals(true, hs.contains("abcd"));
    }
    
    @Test
    public void testIncreaseCapacity() {
        HshSet<String> hs= new HshSet<String>();
        hs.add("a");
        hs.add("a");
        hs.add("b");
        hs.add("c");
        hs.add("d");
        hs.add("e");
        assertEquals(5, hs.size());
        assertEquals(10, hs.capacity());
        hs.add("f");
        assertEquals(true, hs.contains("a"));
        assertEquals(true, hs.contains("b"));
        assertEquals(true, hs.contains("c"));
        assertEquals(6, hs.size());
        assertEquals(24, hs.capacity());
        assertEquals(1, hs.getRehashes());
        assertEquals(true, hs.contains("a"));
        assertEquals(true, hs.contains("b"));
        assertEquals(true, hs.contains("c"));
        
        hs.add("1");
        hs.add("2");
        hs.add("3");
        hs.add("4");
        hs.add("5");
        hs.add("6");
        assertEquals(24, hs.capacity());
        assertEquals(1, hs.getRehashes());
        
        hs.add("7");
        assertEquals(52, hs.capacity());
        assertEquals(2, hs.getRehashes()); 
    }
    
    @Test
    public void testRemove() {
        HshSet<String> hs= new HshSet<String>();
        for (int i= 0; i < 100; i= i+1) {
            hs.add(i + "");
        }
        
        for (int i= 0; i < 100; i= i+1) {
            boolean x= hs.remove(i + "");
            assertEquals(true, x);
            assertEquals(false, hs.contains(i + ""));
            if (i != 0) {
                x= hs.remove(i + "");
                assertEquals(false, x);
            }
        }
        
        assertEquals(10, hs.capacity());
        assertEquals(0, hs.size());
        assertEquals(9, hs.getRehashes());
    }
    
    @Test
    public void testPt() {
        @SuppressWarnings("unused")
        HshSet<Pt> s= new HshSet();
        s.add(new Pt(3, 5));
        s.add(new Pt(3, 5));
    }
   
}
