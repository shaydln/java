package queue;

import java.util.Objects;

public abstract class AbstractQueue implements Queue{
    protected int size = 0;
    protected abstract void enqueueImpl(Object element);
    protected abstract void dequeueImpl();
    protected abstract void clearImpl();
    protected abstract Object elementImpl();
    protected abstract int bothIndexOfImpl(Object element, int tempSize, int answerForNotFound, int type);
    //Pred: true
    //Post: head' = 0 && size' = 0 && for all i (Object) in elements': elements'[i] = null && immutable
    @Override
    public void clear() {
        clearImpl();
        size = 0;
    }
    // Pred: element != null
    // Post: immutable && n' = (n + 1) % elements.length && a[n'] = element
    @Override
    public void enqueue (Object element) {
        Objects.requireNonNull(element);
        enqueueImpl(element);
        size++;
    }
    //Pred: head' is in [0, elements'.length-1]
    //Post: res = element'[head'] && immutable
    @Override
    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    //Pred: size >= 1
    //Post: head' is in [0, elements'.length-1] && immutable && res = elements[head'] && elements'[front'] = null && size' = size - 1
    @Override
    public Object dequeue() {
        Object tempObject = element();
        dequeueImpl();
        size--;
        return tempObject;
    }
    //Pred: true
    //Post: res = (size' == 0) && immutable
    public boolean isEmpty() {
        return size == 0;
    }
    //Pred: true
    //Post: res = size' && immutable
    public int size() {
        return size;
    }
    //Pred: element != null
    //Post: max i (where i 0...size'): elements'[i] equals element or -1 if it doesn't contains && immutable
    @Override
    public int lastIndexOf(Object element) {
        Objects.requireNonNull(element);
        return bothIndexOfImpl(element, size, -1, 2);
    }
    //Pred: element != null
    //Post: min i (where i 0...size'): elements'[i] equals element or -1 if it doesn't contains && immutable
    @Override
    public int indexOf(Object element) {
        Objects.requireNonNull(element);
        return bothIndexOfImpl(element, size, -1, 1);
    }
}
