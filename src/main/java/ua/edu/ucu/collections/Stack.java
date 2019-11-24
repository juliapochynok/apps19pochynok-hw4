package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {

    private ImmutableLinkedList stack;

    public Stack() { stack = new ImmutableLinkedList(); }

    public Stack(Object[] objs) { stack = new ImmutableLinkedList(objs); }

    public Object peek() {
        return stack.getLast();
    }

    public Object pop()
    {
        Object first = stack.getLast();
        stack = stack.removeLast();
        return first;
    }

    public void push(Object e) { stack = stack.addLast(e); }

}
