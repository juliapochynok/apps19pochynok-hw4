package ua.edu.ucu.collections.immutable;
import java.lang.Math;
import java.util.Objects;

public final class ImmutableLinkedList implements ImmutableList {

    public Node head;
    public Node tail;

    public ImmutableLinkedList() {
        head = new Node();
        tail = head;
    }

    public ImmutableLinkedList(Object[] elements) {
        head = new Node(elements[0]);
        Node currNode = head;
        for (int i = 1; i < elements.length; i++) {
            Node newNode = new Node(elements[i]);
            currNode.setNext(newNode);
            currNode = newNode;
           }
        tail = currNode;
        }

    private ImmutableLinkedList makeNew() {
        ImmutableLinkedList newList =
                new ImmutableLinkedList(new Object[] {head.getValue()});
        Node currNodeThis = head.getNext();
        Node currNodeNew = newList.head;
        while (currNodeThis != null) {
            currNodeNew.setNext(new Node(currNodeThis.getValue()));
            newList.tail = currNodeNew.getNext();
            currNodeThis = currNodeThis.getNext();
            currNodeNew = currNodeNew.getNext();
        }
        return newList;
    }

    private Node nodeAtIndex(int ind) {
        Node currNode = head;
        for (int counter = 0; counter < ind; counter++) {
            currNode = currNode.getNext();
        }
        return currNode;
    }

    private void checkIndex(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size(), e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        checkIndex(index);
        Object[] withE = new Object[1];
        withE[0] = e;
        return addAll(index, withE);

        //Залишила також внизу минулий код, який був зданий
//        if (index > size() || index < 0) {
//            throw new IndexOutOfBoundsException();
//        }
        //        else if (index == 0) {
//            ImmutableLinkedList newList = makeNew();
//            newList.head.setValue(e);
//            newList.tail = newList.head;
//            return newList;
//        }
//        else {
//            ImmutableLinkedList newList = makeNew();
//            Node previousNode = newList.nodeAtIndex(index - 1);
//            Node currNode = newList.nodeAtIndex(index);
//            Node newNode = new Node(e);
//            newNode.setNext(currNode);
//            previousNode.setNext(newNode);
//            return newList;
//        }
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        checkIndex(index);
        if (index == 0) {
            ImmutableLinkedList newList = new ImmutableLinkedList(c);
            if (head.getValue() != null) { newList.tail.setNext(head); }
            return newList;
        }
        else {
            ImmutableLinkedList newList = makeNew();
            Node previousNode = newList.nodeAtIndex(index - 1);
            Node currNode = newList.nodeAtIndex(index);
            int newInd = index - 1;
            for (Object el: c) {
                previousNode.setNext(new Node(el));
                previousNode = previousNode.getNext();
                newInd++;
            }
            Node newNode = newList.nodeAtIndex(newInd);
            newNode.setNext(currNode);
            if (currNode == null) { newList.tail = newNode; }
            return newList;
        }
    }

    @Override
    public Object get(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return nodeAtIndex(index).getValue();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = makeNew();
        Node previous = newList.nodeAtIndex(index - 1);
        Node next = newList.nodeAtIndex(index + 1);
        previous.setNext(next);
        return newList;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = makeNew();
        newList.nodeAtIndex(index).setValue(e);
        return newList;
    }

    @Override
    public int indexOf(Object e) {
        Node currNode = head;
        for (int i = 0; i < size(); i++) {
            if (equals(currNode.getValue(), e)) { return i; }
            currNode = currNode.getNext();
        }
        return -1;
    }

    private boolean equals(Object given, Object val) {
        if (this == val)
            return true;
        if (val == null || given.getClass() != val.getClass()) return false;
        return Objects.equals(given, val);
    }

//    private double hashCode(Object val) {
//        int p = 37;
//        double d = 0.107;
//        return Math.pow(p * p * (int) val, d);
//    }

    @Override
    public int size() {
        Node currNode = head;
        int counter = 0;
        if (head.getValue() == null) { return counter; }
        while (currNode != null) {
            currNode = currNode.getNext();
            counter++;
        }
        return counter;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        for (int i = 0; i < size(); i++) {
            array[i] = nodeAtIndex(i).getValue();
        }
        return  array;
    }

    @Override
    public String toString() {
        StringBuilder strList = new StringBuilder();
        Node currNode = head;
        while (currNode != null && currNode.getValue() != null) {
            strList.append(currNode.getValue());
            currNode = currNode.getNext();
            if (currNode != null) { strList.append(", "); }
        }
        return strList.toString();
    }

    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList newList = makeNew();
        if (newList.size() == 0) {
            newList.head.setValue(e);
            newList.tail = newList.head;
        }
        else {
            Node currHead = newList.head;
            newList.head = new Node(e);
            newList.head.setNext(currHead);
        }
        return newList;
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList newList = makeNew();
        if (newList.head.getValue() == null) {
            newList.head.setValue(e);
            newList.tail = newList.head;
        }
        else {
            newList.tail.setNext(new Node(e));
            newList.tail = newList.tail.getNext();
        }
        return newList;
    }

    public Object getFirst() {
        if (size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        return head.getValue();
    }

    public Object getLast() {
        if (size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        return tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList newList = makeNew();
        newList.head = newList.head.getNext();
        return newList;
    }

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList newList = makeNew();
        newList.tail = newList.nodeAtIndex(size() - 2);
        newList.tail.setNext(null);
        return newList;
    }

}
