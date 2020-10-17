public class ArrayDeque<T> {
    /** ITEMS array. */
    private T[] items;

    /** Number of non null elements in ITEMS. */
    private int size;

    /** The index to place element when inserting first. */
    private int nextFirst;

    /** The index to place element when inserting last. */
    private int nextLast;


    /** Instantiate a empty array deque that starts with 8 memory boxes. */
    ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 2;
        nextLast = 3;
    }

    /** Add element X to the front of the array deque (at next first index). */
    void addFirst(T x) {
        resizeUpIfNeeded(nextFirst);

        items[nextFirst] = x;

        nextFirst = nextIndex("nextFirst");

        size += 1;
    }

    /** Add item X as the last element of the array deque
     * (at the next last index). */
    void addLast(T x) {
        resizeUpIfNeeded(nextLast);
        items[nextLast] = x;

        nextLast = nextIndex("nextLast");

        size += 1;
    }

    /** Removes and returns the first element of the array,
     * returns null if there are no elements. */
    T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T toReturn = this.get(0);

        int toRemoveIndex = actualFirstIndex();
        nextFirst = actualFirstIndex();

        items[toRemoveIndex] = null;

        size -= 1;

        resizeDownIfNeeded();

        return toReturn;
    }

    /** Returns and removes the last item in the array,
     * returns null if array has no elements.*/
    T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T toReturn = items[actualLastIndex()];

        int toRemoveIndex = actualLastIndex();
        nextLast = actualLastIndex();

        items[toRemoveIndex] = null;

        size -= 1;

        resizeDownIfNeeded();

        return toReturn;
    }

    /** Returns the element at the INDEX index of the array. */
    T get(int index) {
        int returnIndex = nextFirst + 1 + index;

        if (returnIndex >= items.length) {
            returnIndex = returnIndex - items.length; /* Shouldn't use size here since size changes*/
        }
        return items[returnIndex];
    }

    /** Returns the number of elements in the array.*/
    int size() {
        return size;
    }

    /** Returns true if array is empty, false otherwise.*/
    boolean isEmpty() {
        return size == 0;
    }



    /** A helper function to returns what index (FUNC) nextFirst or nextLast
     * will end up on after a call
     * to one of those functions. */
    private int nextIndex(String func) {
        if (func == "nextFirst") {
            if (nextFirst == 0) {
                return items.length - 1;
            } else {
                return nextFirst - 1;
            }
        } else { /*next last*/
            if (nextLast == items.length - 1) {
                return 0;
            } else {
                return nextLast + 1;
            }
        }
    }

    /** A helper function that returns the index of actual last. */
    private int actualLastIndex() {
        if (nextLast == 0) {
            return items.length - 1;
        } else {
            return nextLast - 1;
        }
    }

    /** A helper function that returns the index of actual first. */
    private int actualFirstIndex() {
        if (nextFirst == items.length - 1) {
            return 0;
        } else {
            return nextFirst + 1;
        }
    }

    /** A helper function to move nextLast and nextFirst to actual index.*/
    private void moveIndex() {
        this.nextFirst = actualFirstIndex();
        this.nextLast = actualLastIndex();
    }

    /** Resizes up if needed (INDEX is not null). */
    private void resizeUpIfNeeded(int index) {
        if (items[index] != null) {
            resizeUp(items.length * 2);
        }
    }
    /** Resizes array down if needed. */
    private void resizeDownIfNeeded() {
        if (items.length >= 16 && (double) size / (double) items.length < 0.25) {
            resizeSmall((int) (size / 0.25));
        }
    }

    /** Resizes array up to an array of size CAPACITY.*/
    private void resizeUp(int capacity) {
        moveIndex();
        T[] a = (T[]) new Object[capacity];
        int addedCapacity = capacity - items.length;

        if (nextLast < nextFirst) {
            System.arraycopy(items, 0, a, 0, nextLast + 1);
            System.arraycopy(items, nextFirst, a,
                    a.length - items.length + nextFirst, items.length - nextFirst);
            items = a;

            nextLast = nextIndex("nextLast");
            nextFirst = nextFirst - 1 + addedCapacity; /* why is it - 1? */
        } else {
            System.arraycopy(items, nextFirst, a, addedCapacity, size);
            items = a;
            nextFirst = nextFirst + addedCapacity;
            nextLast = nextLast + addedCapacity;

            nextFirst = nextIndex("nextFirst");
            nextLast = nextIndex("nextLast");
        }
    }

    /** Resize array to a smaller array of size CAPACITY. */
    private void resizeSmall(int capacity) {
        moveIndex();
        T[]a = (T[]) new Object[capacity];
        int subtractedCapacity = items.length - capacity;

        if (nextLast < nextFirst) {
            System.arraycopy(items, 0, a, 0, nextLast + 1);
            System.arraycopy(items, nextFirst, a,
                     a.length - items.length + nextFirst, items.length - nextFirst);
            items = a;
            nextLast = nextIndex("nextLast");
            nextFirst = nextFirst - subtractedCapacity - 1;
        } else {
            System.arraycopy(items, nextFirst, a, 0, size);
            items = a;
            nextLast = size;
            nextFirst = items.length - 1;
        }
    }

}

/** Need to use some circular element to the array
 *  [1 2 3 0 0 0 0 0 0 0 0]
 *  add first, if not circular, you would need to create a new empty array, copy the elements over with the
 *  new element in the zero index, but you wouldn't be resizing. This take linear time (want to take constant time)
 *  Next last and next first point to the same index, how can I tell which is which?
 *
 *  Track which indices where add first was used and which indices were add last
 *  Condition to be full - items.length = size
 *  Resize - move nextLast 1 index to the left (-1), nextLast 1 index to the right (+1)
 *  add space to the right of last index, left of the first index
 *  resize down  - remove to the left of first, right of last
 *  move nextFirst to actual first, then center that at 2 index of new array
 *  write something to find what is next of nextLast and next for nextFirst
 *  Use if statements (if index of nextLast < nextFirst, nextLast > nextFirst,
 *  or move nextLast to actual last and nextFist to actual first
 *  write a function to move nextLast and nextFirst to actual last and first.
 *  Write a function to check what is the next index over (nextLast at the end of the "array" and needs to loop back</>
 *  Would resize down be the same with the only change is addedCapacity becomes addedCapacity * -1?
 *  Capacity will be size / 0.25 rounded down
 *  only resize down if items.length >= 16
 *  resize at the end of a remove first or remove last
 *  since only resize if size >= 16, resize when there are 3 numbers to an array of size 12 (smallest capacity).
 *  nextLast < nextFirst, Find the number of elements from nextFirst onward.
 *  Go to the resized array, last index - number of elements to find where to start array copy*/
