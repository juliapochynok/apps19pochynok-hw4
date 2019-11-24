//package ua.edu.ucu.collections;
//
//import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
//import ua.edu.ucu.collections.immutable.Node;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//public class Queue<String> implements Iterable<String>{
//
//    public ImmutableLinkedList queue;
//
//    @Override
//    public Iterator<String> iterator() {
//        return new Iter();
//    }
//
//    class Iter implements Iterator<String> {
//        Node current = queue.head;
//
//        public boolean hasNext() {
//            return current != null;
//        }
//
//        public String next() {
//            if (!hasNext())
//                throw new NoSuchElementException();
//            Object next = current.value;
//            current = current.next;
//            return (String) next;
//        }
//
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//    }
//
//    public Queue() {
//        queue = new ImmutableLinkedList();
//    }
//
//    public Queue(Object[] objs) {
//        queue = new ImmutableLinkedList(objs);
//    }
//
//    public Object peek() { return queue.getFirst(); }
//
//    public Object dequeue()
//    {
//        Object first = queue.getFirst();
//        queue = queue.removeFirst();
//        return first;
//    }
//
//    public void enqueue(Object e)
//    {
//        queue = queue.addLast(e);
//    }
//
//}

package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {

    public ImmutableLinkedList queue;

    public Queue() {
        queue = new ImmutableLinkedList();
    }

    public Queue(Object[] objs) {
        queue = new ImmutableLinkedList(objs);
    }

    public Object peek() { return queue.getFirst(); }

    public Object dequeue()
    {
        Object first = queue.getFirst();
        queue = queue.removeFirst();
        return first;
    }

    public void enqueue(Object e)
    {
        queue = queue.addLast(e);
    }

}