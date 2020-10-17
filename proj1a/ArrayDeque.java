public class ArrayDeque<T> {
    /** ITEMS array */
    T[] items;

    /** Number of non null elements in ITEMS */
    int size;

    /** The index to place element when inserting first */
    int nextFirst;

    /** The index to place element when inserting last */
    int nextLast;




    /** Instantiate a empty array deque that starts with 8 memory boxes */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 2;
        nextLast = 3;
    }

    /** Add element X to the front of the array deque (at next first index) */
    public void addFirst(T x) {
        if (items[nextFirst] != null) {
            resizeUp(size * 2);
        }

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
        if (items[nextLast] != null) {
            resizeUp(size * 2);
        }
        items[nextLast] = x;

        if (nextLast == items.length - 1) { /* Shouldn't use size here because size changes */
            nextLast = 0;
        } else {
            nextLast += 1;
        }

        size += 1;
    }

    /** Removes and returns the first element of the array, returns null if there are no elements */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T toReturn = this.get(0);

        int toRemoveIndex;

        if (nextFirst == items.length - 1) {
            toRemoveIndex = 0;
            nextFirst = 0;
        } else {
            toRemoveIndex = nextFirst + 1;
            nextFirst += 1;
        }

        items[toRemoveIndex] = null;

        size -= 1;

        if (items.length >= 16 && (double) size / (double) items.length < 0.25) {
            resizeSmall((int) (size / 0.25));
        }

        return toReturn;
    }

    /** Returns and removes the last item in the array, returns null if array has no elements*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T toReturn = this.get(size - 1);

        int toRemoveIndex;

        if (nextLast == 0) {
            toRemoveIndex = items.length - 1;
            nextLast = items.length - 1;
        } else {
            toRemoveIndex = nextLast - 1;
            nextLast -= 1;
        }

        items[toRemoveIndex] = null;

        size -= 1;

        if (items.length >= 16 && (double) size / (double) items.length < 0.25) {
            resizeSmall((int) (size / 0.25));
        }

        return toReturn;
    }

    /** Returns the element at the INDEX index of the array */
    public T get(int index) {
        int returnIndex = nextFirst + 1 + index;

        if (returnIndex >= items.length) {
            returnIndex = returnIndex - items.length; /* Shouldn't use size here since size changes*/
        }
        return items[returnIndex];
    }

    /** Returns the number of elements in the array*/
    public int size() {
        return size;
    }

    /** Returns true if array is empty, false otherwise */
    public boolean isEmpty() {
        return size == 0;
    }



    /** A helper function to check what index nextFirst or nextLast will end up on after a call
     * to one of those functions */
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

    /** A helper function that returns the index of actual last */
    private int actualLastIndex() {
        if (nextLast == 0) {
            return items.length - 1;
        } else {
            return nextLast - 1;
        }
    }

    /** A helper function that returns the index of actual first */
    private int actualFirstIndex() {
        if (nextFirst == items.length - 1) {
            return 0;
        } else {
            return nextFirst + 1;
        }
    }

    /** A helper function to move nextLast and nextFirst to actual index*/
    private void moveIndex() {
        this.nextFirst = actualFirstIndex();
        this.nextLast = actualLastIndex();
    }

    /** Resizes array up*/
    private void resizeUp(int capacity) {
        moveIndex();
        T[] a = (T[]) new Object[capacity];
        int addedCapacity = capacity - items.length;

        if (nextLast < nextFirst) {
            System.arraycopy(items, 0, a, 0, nextLast + 1);
            System.arraycopy(items, nextFirst, a,
                    nextLast + 1 + addedCapacity, items.length - nextFirst);
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

    /** Resize array smaller */
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
