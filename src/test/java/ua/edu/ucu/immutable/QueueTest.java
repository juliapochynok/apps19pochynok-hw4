package ua.edu.ucu.immutable;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.collections.Queue;

import static org.junit.Assert.*;

public class QueueTest {

    private Queue queue1;
    private Queue queue2;
    private Queue queue3;

    @Before
    public void setUp()  {
        queue1 = new Queue(new Object[] {1, 2, 3, 4});
        queue2 = new Queue();
        queue3 = new Queue(new Object[] {7, 11, 56});
    }

    @Test
    public void testPeek() {
        Object first = queue1.peek();
        Object second = queue3.peek();
        assertEquals(first, 1);
        assertEquals(second, 7);
    }

    @Test
    public void testDequeue() {
        Object first = queue1.dequeue();
        Object second = queue3.dequeue();
        Queue expected1 = new Queue(new Object[] {2, 3, 4});
        assertEquals(first, 1);
        assertEquals(second, 7);
        assertEquals(queue1.peek(), expected1.peek());
    }

    @Test
    public void testEnqueue() {
        queue2.enqueue('w');
        queue2.enqueue('s');
        assertEquals(queue2.dequeue(), 'w');
        assertEquals(queue2.dequeue(), 's');
    }
}
