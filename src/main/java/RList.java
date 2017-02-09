
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by LuMoR on 06.02.2017.
 */
public class RList<E> extends AbstractSequentialList<E> implements List<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public int size() {
        return size;
    }

    private void linkFirst(E el){
        final Node el_first = first;
        final Node newNode = new Node(null, el, el_first);
        first = newNode;
        if (el_first == null)
            last = newNode;
        else
            el_first.prev =newNode;

        size++;

    }

    private void linkLast(E el){
        final Node el_last = last;
        final Node newNode = new Node(el_last, el, null);
        last = newNode;
        if (el_last == null)
            first = newNode;
        else
            el_last.next = newNode;
        size++;

    }

    private void linkBefore(E el, Node<E> next) {
        // assert succ != null;
        final Node<E> pred = next.prev;
        final Node<E> newNode = new Node(pred, el, next);
        next.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    private Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    public boolean contains(Object o) {
        if (indexOf(o) != -1)
            return true;
        else
            return false;
    }


    public boolean add(E el) {
        linkLast(el);
        return true;
    }

    public void add(int index, E el) {

        if (index >= 0 && index <= size) {
            if (index == size)
                linkLast(el);
            else
                linkBefore(el, node(index));
        }

    }

    public boolean addAll(Collection c) {
        Object[] collArray = c.toArray();
        int numColArray = collArray.length;
        if (numColArray == 0)
            return false;

        for (Object el : collArray) {
            linkLast((E)el);
        }

        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {

        Iterator<? extends E> collIter = c.iterator();
        if (!collIter.hasNext()) {
            return false;
        }

        while (collIter.hasNext()){
            linkBefore(collIter.next(),node(index));
        }

        return true;
    }

    public E get(int index) {
        if (index >= 0 && index <= size) {
            return node(index).item;
        }
        else
            return null;
    }

    public E set(int index, E newValue) {
        /* Проверить индекс вхождения */
            Node<E> el = node(index);
            E oldValue = el.item;
            el.item = newValue;
            return oldValue;
    }

    private E unlinkFirst(Node<E> f) {

        final E itemvalue = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return itemvalue;
    }

    private E unlinkLast(Node<E> f) {

        final E itemvalue = f.item;
        final Node<E> prev = f.prev;
        f.item = null;
        f.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return itemvalue;
    }

    private E unlinkAny(Node<E> f) {

        final E itemvalue = f.item;
        final Node<E> next = f.next;
        final Node<E> prev = f.prev;

        if ( prev == null){
            first = next;
        } else {
            prev.next = next;
            f.prev = null;
        }
        if ( next == null){
            last = prev;
        } else {
            next.prev = prev;
            f.next = null;
        }

        f.item = null;
        size--;
        return itemvalue;
    }

    public boolean remove(Object o) {
        int io = indexOf(o);
        if (io != -1) {
           unlinkAny(node(io));
           return true;
        }
        else return false;
    }

    public E remove(int index) {
        if (index >= 0 && index <= size) {
            return unlinkAny(node(index));
        }
        else
            return null;
    }

    public boolean removeAll(Collection c) {

        Iterator collIter = c.iterator();
        if (!collIter.hasNext()) {
            return false;
        }

        while (collIter.hasNext()){
            remove((E)collIter.next());
        }

        return true;
    }

    public void clear() {

        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }


    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> el = first; el != null; el = el.next) {
                if (el.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> el = first; el != null; el = el.next) {
                if (o.equals(el.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> el = last; el != null; el = el.prev) {
                index--;
                if (el.item == null)
                    return index;
            }
        } else {
            for (Node<E> el = last; el != null; el = el.prev) {
                index--;
                if (o.equals(el.item))
                    return index;
            }
        }
        return -1;
    }

    public Iterator iterator() {
        return null;
    }

    public ListIterator listIterator() {
        return null;
    }

    public ListIterator listIterator(int index) {
        return null;
    }

    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    public Object[] toArray(Object[] a) {

        return new Object[0];
    }

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public E previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlinkAny(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.item = e;
        }

        public void add(E e) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.item);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    static final class LLSpliterator<E> implements Spliterator<E> {
        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        final RList<E> list; // null OK unless traversed
        Node<E> current;      // current node; null until initialized
        int est;              // size estimate; -1 until first needed
        int expectedModCount; // initialized when est set
        int batch;            // batch size for splits

        LLSpliterator(RList<E> list, int est, int expectedModCount) {
            this.list = list;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getEst() {
            int s; // force initialization
            final RList<E> lst;
            if ((s = est) < 0) {
                if ((lst = list) == null)
                    s = est = 0;
                else {
                    expectedModCount = lst.modCount;
                    current = lst.first;
                    s = est = lst.size;
                }
            }
            return s;
        }

        public long estimateSize() { return (long) getEst(); }

        public Spliterator<E> trySplit() {
            Node<E> p;
            int s = getEst();
            if (s > 1 && (p = current) != null) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                Object[] a = new Object[n];
                int j = 0;
                do { a[j++] = p.item; } while ((p = p.next) != null && j < n);
                current = p;
                batch = j;
                est = s - j;
                return Spliterators.spliterator(a, 0, j, Spliterator.ORDERED);
            }
            return null;
        }

        public void forEachRemaining(Consumer<? super E> action) {
            Node<E> p; int n;
            if (action == null) throw new NullPointerException();
            if ((n = getEst()) > 0 && (p = current) != null) {
                current = null;
                est = 0;
                do {
                    E e = p.item;
                    p = p.next;
                    action.accept(e);
                } while (p != null && --n > 0);
            }
            if (list.modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }

        public boolean tryAdvance(Consumer<? super E> action) {
            Node<E> p;
            if (action == null) throw new NullPointerException();
            if (getEst() > 0 && (p = current) != null) {
                --est;
                E e = p.item;
                current = p.next;
                action.accept(e);
                if (list.modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                return true;
            }
            return false;
        }

        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
