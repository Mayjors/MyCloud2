package com.example.algorithm.base;

import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E> implements List<E>{
    private int DEFAULT_CAPACITY = 10;
    private E[] data = (E[]) new Object[DEFAULT_CAPACITY];
    private int size;

    public ArrayList() {
        size = 0;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        if (index <0 || index > size) {
            throw new IllegalArgumentException("add index out of range");
        }
        if (size == data.length) {
            resize(data.length * 2);
        }
        for (int i=size-1; i>=index; i--) {
            data[i+1] = data[i];
        }
        data[index] = element;
        size++;
    }

    private void resize(int newLength) {
        E[] newData = (E[]) new Object[newLength];
        for (int i=0; i<size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public void remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public E remove(int index) {
        if (index <0 || index>= size) {
            throw new IllegalArgumentException("remove index out of range");
        }
        E ret = data[index];
        for (int i=index+1; i<size; i++) {
            data[i-1] = data[i];
        }
        size--;
        if (size == data.length /4 && data.length > DEFAULT_CAPACITY) {
            resize(data.length / 2);
        }
        return ret;
    }

    @Override
    public E get(int index) {
        if (index<0 || index>= size) {
            throw new IllegalArgumentException("get index out of range");
        }
        return data[index];
    }

    @Override
    public E set(int index, E element) {
        if (index<0 || index>= size) {
            throw new IllegalArgumentException("set index out of range");
        }
        E ret = data[index];
        data[index] = element;
        return ret;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        for (int i=0; i<size; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void sort(Comparator<E> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("comparator can not null");
        }
        for (int i=0; i<size-1; i++) {
            for (int j=i+1; j<size; j++) {
                if (comparator.compare(data[i], data[j]) > 0) {
                    swap(i, j);
                }
            }
        }
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IllegalArgumentException("0 <= fromIndex <= toIndex <= size");
        }
        ArrayList<E> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(data[i]);
        }
        return subList;
    }

    @Override
    public void swap(int i, int j) {
        if (i<=0 || i>= size || j<0 || j>= size) {
            throw new IllegalArgumentException("swap index out of range");
        }
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }


    private class ArrayListIterator implements Iterator {
        private int cur = 0;

        @Override
        public boolean hasNext() {
            return cur < size;
        }

        @Override
        public Object next() {
            return data[cur++];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (isEmpty()) {
            sb.append("]");
        } else {
            for (int i = 0; i < size; i++) {
                sb.append(data[i]);
                if (i == size - 1) {
                    sb.append("]");
                } else {
                    sb.append(",");
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}
