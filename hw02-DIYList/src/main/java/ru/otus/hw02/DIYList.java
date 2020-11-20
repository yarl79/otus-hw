package ru.otus.hw02;

import java.util.*;

public class DIYList<E> implements List<E> {

    private Object[] elements = new Object[10];
    private int size = 0;

    private class ListItr implements ListIterator<E> {

        private  int cursor = 0;
        private int lastRet = -1;

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public E next() {
            if (cursor >= size)
                throw new NoSuchElementException();
            cursor++;
            return (E) elements[lastRet = cursor - 1];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            DIYList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public E previous() {
            if ( cursor - 1 < 0 ) {
                throw new NoSuchElementException();
            }
            return (E) elements[lastRet = cursor - 1];
        }

        public void set(E e) {
                DIYList.this.set(cursor - 1, e);
        }

        public void add(E e) {
            DIYList.this.add(cursor, e);
            cursor++;
            lastRet = -1;
        }
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkSize() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) return false;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListItr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        elements[size++] = e;
        checkSize();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        indexCheck(index);
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        indexCheck(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
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
    public ListIterator<E> listIterator() {
        return new ListItr();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public void sort(Comparator<? super E> c) {
        List.super.sort(c);
    }
}
