import java.util.*;

public class SortedLinkedList<T> {
    private Node<T> firstElement;
    private Node<T> lastElement;
    private int size;

    private Comparator comparator;

    public SortedLinkedList(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public void addAll(Collection<T> values) {
        values.forEach(this::add);
    }

    public boolean add(T t) {
        Node<T> newEl = new Node<>(t);
        if (this.firstElement == null) {
            this.firstElement = newEl;
            this.lastElement = newEl;
        } else if (comparator.compare(t, this.firstElement.item) <= 0) {
            // adding on first position
            newEl.next = this.firstElement;
            this.firstElement.prev = newEl;
            this.firstElement = newEl;
        } else if (comparator.compare(this.lastElement.item, t) <= 0) {
            // adding on last position
            newEl.prev = this.lastElement;
            this.lastElement.next = newEl;
            this.lastElement = newEl;
        } else {
            // adding in the middle
            Node<T> curr = this.firstElement;
            while (comparator.compare(curr.item, t) < 0) {
                curr = curr.next;
            }

            newEl.prev = curr.prev;
            newEl.prev.next = newEl;
            newEl.next = curr;
            curr.prev = newEl;
        }

        size++;
        return true;
    }

    public boolean remove(Object o) {
        Node<T> curr = this.firstElement;
        while (curr != null && comparator.compare(curr.item, o) < 0) {
            curr = curr.next;
        }

        while (curr != null && comparator.compare(curr.item, o) == 0) {
            // remove curr
            removeNode(curr);
            curr = curr.next;
        }
        return false;
    }

    public void clear() {
        firstElement = null;
        lastElement = null;
        size = 0;
    }

    public T get(int index) {
        validateIndex(index);

        Node<T> cur;
        if (index < this.size / 2) {
            // start from first element
            cur = this.firstElement;
            var aux = 0;
            while (aux != index) {
                cur = cur.next;
                aux++;
            }
        } else {
            // start from last element
            cur = this.lastElement;
            var aux = size - 1;
            while (aux != index) {
                cur = cur.prev;
                aux--;
            }
        }
        return cur.item;
    }

    public T remove(int index) {
        Node<T> cur = node(index);
        removeNode(cur);
        return cur.item;
    }

    public Iterator<T> iterator(int index) {
        return new SortedLinkedListIterator(index);
    }

    public Iterator<T> iterator() {
        return new SortedLinkedListIterator(0);
    }

    public int indexOf(Object o) {
        return indexOfInternal(o, false);
    }

    public int lastIndexOf(Object o) {
        return indexOfInternal(o, true);
    }

    private int indexOfInternal(Object o, boolean getLast) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = firstElement; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            Node<T> x = this.firstElement;
            while (x != null && comparator.compare(x.item, o) < 0) {
                x = x.next;
                index++;
            }

            if (x != null) {
                while (getLast && comparator.compare(x.item, x.next.item) == 0) {
                    x = x.next;
                    index++;
                }

                if (comparator.compare(x.item, o) == 0) {
                    return index;
                }
            }
        }
        return -1;
    }

    private void removeNode(Node<T> node) {
        if (node.prev == null) {
            // remove from first position
            this.firstElement = this.firstElement.next;
            this.firstElement.prev = null;
        } else if (node.next == null) {
            // remove from last position
            this.lastElement = this.lastElement.prev;
            this.lastElement.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;
    }

    private void validateIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> node(int index) {
        validateIndex(index);
        Node<T> cur;
        if (index < this.size / 2) {
            // start from first element
            cur = this.firstElement;
            var aux = 0;
            while (aux != index) {
                cur = cur.next;
                aux++;
            }
        } else {
            // start from last element
            cur = this.lastElement;
            var aux = size - 1;
            while (aux != index) {
                cur = cur.prev;
                aux--;
            }
        }

        return cur;
    }


    private class SortedLinkedListIterator implements Iterator<T> {
        private Node<T> lastReturned;
        private Node<T> next;
        private int nextIndex;

        SortedLinkedListIterator(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? lastElement : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }
    }

    private static class Node<Q> {
        Q item;
        Node<Q> next;
        Node<Q> prev;

        Node(Node<Q> prev, Q element, Node<Q> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        Node(Q element) {
            this.item = element;
        }

        public void setItem(Q item) {
            this.item = item;
        }

        public void setNext(Node<Q> next) {
            this.next = next;
        }

        public void setPrev(Node<Q> prev) {
            this.prev = prev;
        }
    }
}
