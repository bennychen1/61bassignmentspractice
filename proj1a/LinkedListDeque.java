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
        sentinel = new IntNode(null, null, null); /* Generic, so sentinel can't be any one type */
    }

    /**Add item X to the front of the list */
    public void addFirst(T x) {
        sentinel.next = new IntNode(x, sentinel.next, sentinel);
        this.size += 1;
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
