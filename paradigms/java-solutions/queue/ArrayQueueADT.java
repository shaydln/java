package queue;

import java.util.Arrays;
import java.util.Objects;
/*
    Model: elements[0] ... elements[size-1]
    Invariant: size >= 0 && if(size > 0): for all indexes: elements[i] != null
    Let immutable: for all indexes: elements'[i] != elements[i] && head' = head && size' = size
*/
public class ArrayQueueADT {
    private Object[] elements = new Object[0];
    private int head = 0;
    private int size = 0;
    // Pred: element != null
    // Post: immutable && n' = (n + 1) % elements.length && a[n'] = element
    public static void enqueue (ArrayQueueADT queue, Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue, queue.size + 1);
        if (queue.head + queue.size >= queue.elements.length) {
            queue.elements[queue.head + queue.size - queue.elements.length] = element;
        } else {
            queue.elements[queue.head + queue.size] = element;
        }
        queue.size++;
    }
    //Pred: head' is in [0, elements'.length-1]
    //Post: res = element'[head'] && immutable
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.head];
    }
    //Pred: size >= 1
    //Post: head' is in [0, elements'.length-1] && immutable && res = elements[head'] && elements'[front'] = null && size' = size - 1
    public static Object dequeue(ArrayQueueADT queue) {
        Object tempObject = element(queue);
        queue.elements[queue.head] = null;
        if (queue.head == queue.elements.length - 1) {
            queue.head = 0;
        } else {
            queue.head++;
        }
        queue.size--;
        return tempObject;
    }
    //Pred: true
    //Post: res = size' && immutable
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    //Pred: elements.length > 0
    //Post: immutable && length <= elements'.length
    private static void ensureCapacity(ArrayQueueADT queue, int length) {
        if (length > queue.elements.length) {
            if (queue.head == 0) {
                queue.elements = Arrays.copyOf(queue.elements, 2 * queue.size + 1);
            } else {
                Object[] tempArray = new Object[queue.size];
                tempArray = toArray(queue);
                queue.elements = Arrays.copyOf(tempArray, 2 * queue.size + 1);
                queue.head = 0;
            }
        }
    }
    //Pred: true
    //Post: res = elements[0] ... elements[size-1]
    public static Object[] toArray(ArrayQueueADT queue) {
        Object[] tempArray = new Object[queue.size];
        if (isEmpty(queue)) {
            return new Object[0];
        }
        int index = 0, tempSize = queue.size, tempHead = queue.head;
        while (tempSize > 0) {
            tempArray[index] = queue.elements[tempHead];
            index++;
            tempHead++;
            tempSize--;
            if (tempHead >= queue.elements.length) {
                tempHead -= queue.elements.length;
            }
        }
        return tempArray;
    }
    //Pred: true
    //Post: res = (size' == 0) && immutable
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    //Pred: true
    //Post: head' = 0 && size' = 0 && for all i (Object) in elements': elements'[i] = null && immutable
    public static void clear(ArrayQueueADT queue) {
        Arrays.fill(queue.elements, null);
        queue.size = 0;
        queue.head = 0;
    }
}
