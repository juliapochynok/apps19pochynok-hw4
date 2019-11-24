package ua.edu.ucu.tries;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.collections.Queue;

import static org.junit.Assert.*;

public class RWayTrieTest {

    private RWayTrie trie1;
    private RWayTrie trie2;
    private Tuple tuple1;
    private Tuple tuple4;

    @Before
    public void setUp()  {
        trie1 = new RWayTrie();
        tuple1 = new Tuple("mama", 4);
        Tuple tuple2 = new Tuple("melody", 6);
        trie1.add(tuple2);
        trie2 = new RWayTrie();
        Tuple tuple3 = new Tuple("lala", 4);
        tuple4 = new Tuple("MAMA", 4);
        trie1.add(tuple2);
        trie2.add(tuple2);
        trie2.add(tuple3);
        trie2.add(tuple1);
    }

    @Test
    public void testAdd() {
        trie1.add(tuple1);
        assertTrue(trie1.contains("mama"));
        trie1.add(tuple4);
        assertTrue(trie1.contains("MAMA"));
     }

    @Test
    public void testContains() {
        assertTrue(trie1.contains("melody"));
        assertFalse(trie1.contains("m"));
    }

    @Test
    public void testDelete() {
        assertTrue(trie1.contains("melody"));
        trie1.delete("melody");
        assertFalse(trie1.contains("melody"));
    }

    @Test
    public void testSize() {
        assertEquals(trie2.size(), 3);
    }

}
