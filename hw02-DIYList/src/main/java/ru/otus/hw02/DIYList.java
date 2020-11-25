package ru.otus.hw02;

import java.util.*;

public class DIYList<E> implements List<E> {

    private Object[] elements = new Object[10];
    private int size = 0;



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
        return ( T[] ) Arrays.copyOf(elements, size);
    }

    @Override
    public boolean add(E e) {
        elements[size++] = e;
        doubleElementsSizeIfNeed();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
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

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Max index - " + ( size - 1 ) + ", passed index - " + index);
        }
    }

    private void doubleElementsSizeIfNeed() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

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
}
