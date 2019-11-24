package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.Objects;

public final class ImmutableArrayList implements ImmutableList {

    private Object[] myList;
    private int elNum;
    private int lstCapacity;

    public ImmutableArrayList() {
        myList = new Object[0];
        elNum = 0;
        lstCapacity = myList.length;
    }

    public ImmutableArrayList(Object[] objs) {
        myList = objs.clone();
        int counter = 0;
        for (Object el : objs) {
            if (el == null) { break; }
            counter++;
        }
        elNum = counter;
        lstCapacity = myList.length;
    }

    private Object[] resize(Object[] currList) {
        Object[] newList;
        if (currList.length == 0) { newList = Arrays.copyOf(currList, 1); }
        else { newList = Arrays.copyOf(currList, currList.length + 1); }
        return newList;
    }

    private Object[] resizeDown(Object[] currList) {
        Object[] newList;
        if (currList.length == 0) { newList = Arrays.copyOf(currList, 1); }
        else { newList = Arrays.copyOf(currList, currList.length - 1); }
        return newList;
    }

    private void checkIndex(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private ImmutableArrayList makeNew() {
        Object[] copyList = new Object[myList.length];
        System.arraycopy(myList, 0, copyList, 0, myList.length);
        return new ImmutableArrayList(copyList);
    }

    @Override
    public ImmutableArrayList add(Object e) {
        Object[] withE = new Object[1];
        withE[0] = e;
        return addAll(elNum, withE);

        //Залишила також внизу минулий код, який був зданий
//        ImmutableArrayList newList = makeNew();
//        if (newList.lstCapacity == 0 || newList.lstCapacity
//                - newList.elNum <= 0) {
//            newList.myList = resize(newList.myList);
//            newList.lstCapacity = newList.lstCapacity + 1;
//        }
//        newList.myList[elNum] = e;
//        newList.elNum++;
//        return newList;
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        checkIndex(index);
            Object[] withE = new Object[1];
            withE[0] = e;
            return addAll(index, withE);

        //Залишила також внизу минулий код, який був зданий
//        if (index > size() || index < 0) {
//            throw new IndexOutOfBoundsException();
//        }
//        else {
//            Object[] workList = new Object[lstCapacity + 1];
//            System.arraycopy(myList, 0, workList, 0, index);
//            workList[index] = e;
//            if (lstCapacity - index >= 0) {
//                System.arraycopy(myList, index,
//                        workList, index + 1, lstCapacity - index);
//            }
//            return new ImmutableArrayList(workList);
//        }
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        ImmutableArrayList newList = makeNew();
        if (newList.lstCapacity < newList.elNum + c.length) {
            newList.myList = resize(newList.myList);
            newList.lstCapacity = newList.lstCapacity + 1;
        }
        return newList.addAll(elNum, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        checkIndex(index);
            Object[] workList = new Object[lstCapacity + 1];
            System.arraycopy(myList, 0, workList, 0, index);
            ImmutableArrayList newList = new ImmutableArrayList(workList);
            int lengthC = c.length;
            int i = 0;
            while (i != c.length) {
                if (newList.lstCapacity - lengthC >= newList.elNum) {
                    int it = newList.elNum;
                    newList.myList[it] = c[i];
                    newList.elNum++;
                    i++;
                    lengthC--;
                } else {
                    newList.myList = resize(newList.myList);
                    newList.lstCapacity = newList.lstCapacity + 1;
                }
            }
            if (newList.lstCapacity < lstCapacity + c.length) {
                newList.myList = resize(newList.myList);
                newList.lstCapacity = newList.lstCapacity + 1;
            }
            for (int e = index; e < elNum; e++) {
                newList.myList[e + c.length] = myList[e];
                newList.elNum++;
            }
            return newList;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return myList[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        checkIndex(index);
            ImmutableArrayList newList = makeNew();
            for (int i = index + 1; i < newList.elNum; i++) {
                newList.myList[i - 1] = newList.myList[i];
            }
            newList.lstCapacity--;
            newList.elNum--;
            newList.myList = resizeDown(newList.myList);
            return newList;
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        if (index >= lstCapacity || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            ImmutableArrayList newList = makeNew();
            newList.myList[index] = e;
            return newList;
        }
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < elNum; i++) {
            if (equals(myList[i], e)) { return i; }
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
        return lstCapacity;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() { return elNum == 0; }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(myList, elNum);
    }

    @Override
    public String toString() {
        StringBuilder strList = new StringBuilder();
        for (int i = 0; i < elNum; i++) {
            strList.append(myList[i]);
            if (i != elNum - 1) { strList.append(", "); }
        }
        return strList.toString();
    }

}
