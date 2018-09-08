package cn.zlq.index;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class TermInfoQueue implements Queue<TermInfo> {
    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<TermInfo> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(TermInfo termInfo) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends TermInfo> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public boolean offer(TermInfo termInfo) {
        return false;
    }

    public TermInfo remove() {
        return null;
    }

    public TermInfo poll() {
        return null;
    }

    public TermInfo element() {
        return null;
    }

    public TermInfo peek() {
        return null;
    }
}
