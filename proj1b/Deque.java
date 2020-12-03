public interface Deque<T> {
    /** Add ITEM to the front of the deque */
    public void addFirst(T item);

    /** Add ITEM to the end of the deque */
    public void addLast(T item);

    /** Returns true if the deque has no items.
     * Returns False otherwise.*/
    default boolean isEmpty() {
        return size() == 0;
    };

    /** Returns the number of items in the deque.
     * The last item in the deque is at the size - 1 index*/
    public int size();

    /** Print all the items in the deque. */
    public void printDeque();

    /** Remove and return the first item in the deque.
     * Null if deque has no items. */
    public T removeFirst();

    /** Remove and return the last item in the deque.
     * Null if no deque has no items. */
    public T removeLast();

    /** Reutrn the item at INDEX-th index of the deque */
    public T get(int index);

}
