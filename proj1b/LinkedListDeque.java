public class LinkedListDeque<T> implements Deque<T>{
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

    /** Creates a deep copy of a LinkedListDeque OTHER */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.size = other.size();

        for (int i = 0; i < other.size(); i++) {
            this.addLast((T) other.get(i));
        }
    }

    @Override
    /**Add item X to the front of the list */
    public void addFirst(T x) {
        sentinel.next = new IntNode(x, sentinel.next, sentinel);
        this.size += 1;

        if (sentinel.prev == sentinel) {
            sentinel.prev = sentinel.next;
        }

        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    /** Add itme X to the end of the list */
    public void addLast(T x) {
        sentinel.prev = new IntNode(x, sentinel, sentinel.prev);
        this.size += 1;

        if (sentinel.next == sentinel) {
            sentinel.next = sentinel.prev;
        }

        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    /** Returns the size of the linked list */
    public int size() {
        return this.size;
    }

    @Override
    /** Removes and returns the first item of the list (after the sentinel) */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        size -= 1;

        T toReturn = sentinel.next.item;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev.next = null;
        sentinel.next.prev.prev = null;
        sentinel.next.prev = sentinel;

        return toReturn;
    }

    @Override
    /** Removes and returns the last item of the list.  */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        T toReturn = sentinel.prev.item;


        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next.prev = null;
        sentinel.prev.next.next = null;
        sentinel.prev.next = sentinel;

        this.size -= 1;

        return toReturn;
    }

    @Override
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

    @Override
    /** Prints the items in the list */
    public void printDeque() {
        IntNode p = sentinel.next;

        StringBuilder toPrint = new StringBuilder();

        for (int i = 0; i < size; i++) {
            toPrint.append(p.item + " ");
        }

        System.out.println(toPrint + "\n");
    }

    /** Recursively returns the INDEX-th item in the list*/
    public T getRecusrive(int index) {
        if (isEmpty() || index > size()) {
            return null;
        }

        if (index == 0) {
            return this.sentinel.next.item;
        }

        LinkedListDeque<T> p = new LinkedListDeque<>(this);
        p.removeFirst();

        return p.getRecusrive(index - 1);
    }


}

