package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueue extends AbstractQueue{
    private Object[] elements = new Object[0];
    private int head = 0;
    protected void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        if (head + size >= elements.length) {
            elements[head + size - elements.length] = element;
        } else {
            elements[head + size] = element;
        }
    }
    protected Object elementImpl() {
        return elements[head];
    }
    protected void dequeueImpl() {
        elements[head] = null;
        if (head == elements.length - 1) {
            head = 0;
        } else {
            head++;
        }
    }
    //Pred: elements.length > 0
    //Post: immutable && length <= elements'.length
    private void ensureCapacity(int length) {
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
    public Object[] toArray() {
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
    protected void clearImpl() {
        Arrays.fill(elements, null);
        head = 0;
    }
    //Pred: element != null && tempSize > 0 && (type == 1 || type == 2)
    //Post: if (type == 1: min) else if (type == 2: max)
    // i (where i 0...size'): elements'[i] equals element or -1 if it doesn't contains && immutable
    protected int bothIndexOfImpl(Object element, int tempSize, int answer, int type) {
        int i = head;
        while (tempSize > 0) {
            if (Objects.equals(elements[i], element)) {
                if (type == 1) {
                    return indexOfChecking(i);
                } else if (type == 2) {
                    answer = lastIndexOfChecking(i);
                }
            }
            tempSize--;
            i++;
            if (i >= elements.length) {
                i -= elements.length;
            }
        }
        return answer;
    }
    private int indexOfChecking(int i) {
        if (i - head >= 0) {
            return i - head;
        } else {
            return i + elements.length - head;
        }
    }
    private int lastIndexOfChecking(int i) {
        int answer;
        if (i - head >= 0) {
            answer = i - head;
        } else {
            answer = i + elements.length - head;
        }
        return answer;
    }
}
// SECOND REALIZATION OF INDEX OF
//        Object[] tempArr = toArray();
//        for (int i = 0; i < size; i++) {
//            if (Objects.equals(tempArr[i], element)) {
//                return i;
//            }
//        }
//        return -1;
// SECOND REALIZATION OF LAST INDEX OF
//        Object[] tempArr = toArray();
//        int answer = -1;
//        for (int i = 0; i < size; i++) {
//            if (Objects.equals(tempArr[i], element)) {
//                answer = i;
//            }
//        }
//        return answer;
