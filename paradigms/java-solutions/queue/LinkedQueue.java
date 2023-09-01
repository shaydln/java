package queue;

import java.util.Objects;

public class LinkedQueue extends AbstractQueue{
    private Node head;
    private Node tail;
    protected void enqueueImpl(Object element) {
        Node newNode = new Node(element, null);
        if (isEmpty()) {
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    protected Object elementImpl() {
        return head.value;
    }
    protected void dequeueImpl() {
        if (head.next == null) {
            tail = null;
        }
        head = head.next;
    }
    protected void clearImpl() {
        head = null;
        tail = null;
    }
    private class Node {
        private Object value;
        private Node next;
        private Node(Object value, Node next) {
            Objects.requireNonNull(value);
            this.value = value;
            this.next = next;
        }
    }
    //Pred: element != null && tempsize > 0 && (type == 1 || type == 2)
    //Post: if (type == 1: min) else if (type == 2: max)
    // i (where i 0...size'): elements'[i] equals element or -1 if it doesn't contains && immutable
    protected int bothIndexOfImpl(Object element, int tempSize, int answer, int type) {
        int index = 0;
        Node newNode = head;
        while (tempSize > 0) {
            if (Objects.equals(newNode.value, element)) {
                if (type == 1) {
                    return index;
                } else if (type == 2) {
                    answer = index;
                }
            }
            newNode = newNode.next;
            tempSize--;
            index++;
        }
        return answer;
    }

    // abcd
}
