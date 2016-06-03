package myUtil;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by wookie on 6/2/16.
 */
public class MyArrayList<T> implements List<T> {
    private static final int START_ENTRY = 10;
    private static final int ADDED_MEMORY = 16;

    private Object[] array;
    private int size = 0;
    private int modCount = 0;

    public MyArrayList() {
        array = new Object[START_ENTRY];
    }

    private MyArrayList(int initialEntry) {
        array = new Object[initialEntry];
    }

    private void addArrayMemory() {
        Object[] temp = new Object[size + ADDED_MEMORY];
        System.arraycopy(array, 0, temp, 0, array.length);
        array = temp;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - array.length > 0) {
            addArrayMemory();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        this.ensureCapacity(size + 1);
        this.array[size] = o;
        size++;
        modCount++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                this.remove(i);
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public void replaceAll(UnaryOperator operator) {

    }

    @Override
    public void sort(Comparator c) {

    }

    @Override
    public void clear() {

    }

    private boolean checkIndex(int index) {
        return ((index <= size) && (index >= 0));
    }

    public T get(int index) {
        if(!checkIndex(index)) {
            throw new IllegalArgumentException();
        }

        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        T temp;
        if(!checkIndex(index)) {
            throw new IllegalArgumentException();
        }
        temp = (T) array[index];
        array[index] = element;
        modCount++;
        return temp;
    }

    @Override
    public void add(int index, Object element) {
        if(!checkIndex(index)) {
            throw new IllegalArgumentException();
        }
        ensureCapacity(size+1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = 0;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        T temp = null;
        if(!checkIndex(index)) {
            throw new IllegalArgumentException();
        }
        temp = (T) array[index];
        System.arraycopy(array, index+1, array, index, size - index - 1);
        array[size-1] = null;
        size--;
        modCount++;
        return temp;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return new ListIterator<T>(){
            int index = 0;
            int modCount = MyArrayList.this.modCount;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T) array[index++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                return (T) array[--index];
            }

            @Override
            public int nextIndex() {
                return index + 1;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(index);
                index--;
                modCount++;
            }

            @Override
            public void set(T t) {
                MyArrayList.this.set(index, t);
                modCount++;
            }

            @Override
            public void add(T t) {
                MyArrayList.this.add(index, t);
                modCount++;
            }

            private void checkMods() {
                if (modCount != MyArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }


}
