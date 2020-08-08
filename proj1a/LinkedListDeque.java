public class LinkedListDeque<T> {
    IntNode sentinel;
    private int size;

    /** Class only used internally */
    private class IntNode {
        T item;
        IntNode next;
        IntNode prev;

        private IntNode(T item, IntNode next, IntNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    /**Constructor for empty linked list */
    public LinkedListDeque(){
        sentinel = new IntNode(null, null, null); /* Generic, so sentinel can't be any one type, choose null */
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**Add item X to the front of the list */
    public void addFirst(T x) {
        sentinel.next = new IntNode(x, sentinel.next, sentinel);
        this.size += 1;

        if (sentinel.prev == sentinel) {
            sentinel.prev = sentinel.next;
        }

        sentinel.next.next.prev = sentinel.next;
    }

    /** Add itme X to the end of the list */
    public void addLast(T x) {
        sentinel.prev = new IntNode(x, sentinel, sentinel.prev);
        this.size += 1;

        if (sentinel.next == sentinel) {
            sentinel.next = sentinel.prev;
        }

        sentinel.prev.prev.next = sentinel.prev;
    }

    /** Returns the size of the linked list */
    public int size() {
        return this.size;
    }

    /** Returns true is linkedlist is empty, false otherwise */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the Ith item of the linked list - start at 0 index.
     *If I is larger than size, return null*/
    public T get(int i) {
        if (i > size || isEmpty()) {
            return null;
        }

        IntNode p = sentinel.next;

        while (i > 0) {
            p = p.next;
            i -= 1;
        }

        return p.item;

    }


}
