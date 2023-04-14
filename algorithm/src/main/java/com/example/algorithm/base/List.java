package com.example.algorithm.base;

import java.util.Comparator;

public interface List<E> extends Iterable<E> {
    public void add(E element);
    public void add(int index, E element);
    public void remove(E eelement);
    public E remove(int index);
    public E get(int index);
    public E set(int index, E element);
    public int size();
    public int indexOf(E element);
    public boolean contains(E element);
    public boolean isEmpty();
    public void clear();
    public void sort(Comparator<E> comparator);
    public List<E> subList(int fromIndex, int toIndex);
    public void swap(int i, int j);
}
