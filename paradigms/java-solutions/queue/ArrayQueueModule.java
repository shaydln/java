package queue;

import java.util.Arrays;
import java.util.Objects;
/*
    Model: elements[0] ... elements[size-1]
    Invariant: size >= 0 && if(size > 0): for all indexes: elements[i] != null
    Let immutable: for all indexes: elements'[i] != elements[i] && head' = head && size' = size
*/
public class ArrayQueueModule {
    private static Object[] elements = new Object[0];
    private static int head = 0;
    private static int size = 0;
    // Pred: element != null
    // Post: immutable && n' = (n + 1) % elements.length && a[n'] = element
    public static void enqueue (Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size + 1);
        if (head + size >= elements.length) {
            elements[head + size - elements.length] = element;
        } else {
            elements[head + size] = element;
        }
        size++;
    }
    //Pred: head' is in [0, elements'.length-1]
    //Post: res = element'[head'] && immutable
    public static Object element() {
        assert size > 0;
        return elements[head];
    }
    //Pred: size >= 1
    //Post: head' is in [0, elements'.length-1] && immutable && res = elements[head'] && elements'[front'] = null && size' = size - 1
    public static Object dequeue() {
        Object tempObject = element();
        elements[head] = null;
        if (head == elements.length - 1) {
            head = 0;
        } else {
            head++;
        }
        size--;
        return tempObject;
    }
    //Pred: true
    //Post: res = size' && immutable
    public static int size() {
        return size;
    }
    //Pred: elements.length > 0
    //Post: immutable && length <= elements'.length
    private static void ensureCapacity(int length) {
        if (length > elements.length) {
            if (head == 0) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            } else {
                Object[] tempArray = new Object[size];
                tempArray = toArray();
                elements = Arrays.copyOf(tempArray, 2 * size + 1);
                head = 0;
            }
        }
    }
    //Pred: true
    //Post: res = elements[0] ... elements[size-1]
    public static Object[] toArray() {
        Object[] tempArray = new Object[size];
        if (isEmpty()) {
            return new Object[0];
        }
        int index = 0, tempSize = size, tempHead = head;
        while (tempSize > 0) {
            tempArray[index] = elements[tempHead];
            index++;
            tempHead++;
            tempSize--;
            if (tempHead >= elements.length) {
                tempHead -= elements.length;
            }
        }
        return tempArray;
    }
    //Pred: true
    //Post: res = (size' == 0) && immutable
    public static boolean isEmpty() {
        return size == 0;
    }
    //Pred: true
    //Post: head' = 0 && size' = 0 && for all i (Object) in elements': elements'[i] = null && immutable
    public static void clear() {
        Arrays.fill(elements, null);
        size = 0;
        head = 0;
    }
}
