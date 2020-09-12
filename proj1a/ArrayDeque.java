public class ArrayDeque<T> {
    /** ITEMS array */
    T[] items;

    /** Number of non null elements in ITEMS */
    int size;

    /** The index to place element when inserting first */
    int nextFirst;

    /** The index to place element when inserting last */
    int nextLast;

    /** An array to track whether the index was filled by add first (1) or add last (2), or empty (0) */
    int[] track;



    /** Instantiate a empty array deque that starts with 8 memory boxes */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 2;
        nextLast = 3;
        track = new int[8];
    }

    /** Add element X to the front of the array deque (at next first index) */
    public void addFirst(T x) {
        /** if (items[nextFirst] != null) {
            reSize(size * 2);
            nextFirst = items.length - 1;
        } */

        track[nextFirst] = 1;

        items[nextFirst] = x;

        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }

        size += 1;
    }

    /** Add item X as the last element of the array deque (at the next last index) */
    public void addLast(T x) {
        track[nextLast] = 2;
        items[nextLast] = x;

        if (nextLast == size - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }

        size += 1;
    }

    /** Returns the element at the INDEX index of the array */
    public T get(int index) {
        int returnIndex = nextFirst + 1 + index;

        if (returnIndex >= items.length - 1) {
            returnIndex = returnIndex - items.length; /* Shouldn't use size here since size changes*/
        }
        return items[returnIndex];
    }

    /** Returns the number of elements in the array*/
    public int size() {
        return size;
    }

    /** Resizes the array to an array of size CAPACITY*/
    public void reSize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

}

/** Need to use some circular element to the array
 *  [1 2 3 0 0 0 0 0 0 0 0]
 *  add first, if not circular, you would need to create a new empty array, copy the elements over with the
 *  new element in the zero index, but you wouldn't be resizing. This take linear time (want to take constant time)
 *  Next last and next first point to the same index, how can I tell which is which?
 *
 *  Track which indices where add first was used and which indices were add last*/
