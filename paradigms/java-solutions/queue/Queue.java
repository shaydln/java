package queue;
/*
    Model: elements[0] ... elements[size-1]
    Invariant: size >= 0 && if(size > 0): for all indexes: elements[i] != null
    Let immutable: for all indexes: elements'[i] != elements[i] && head' = head && size' = size
*/
public interface Queue {
    //Pred: element != null
    //Post: immutable && size' = size + 1 && a[size + 1] = element
    void enqueue(Object element);
    //Pred: size > 0
    //Post: res = element'[head'] && immutable
    Object element();
    //Pred: size > 0
    //Post: immutable && res = elements[head'] && elements'[head'] = null && size' = size - 1
    Object dequeue();
    //Pred: true
    //Post: res = size' && immutable
    int size();
    //Pred: true
    //Post: res = (size' == 0) && immutable
    boolean isEmpty();
    //Pred: true
    //Post: size' = 0 && for all i (Object) in elements': elements'[i] = null && immutable
    void clear();
    //Pred: element != null
    //Post: min i (where i 0...size'): elements'[i] equals element or -1 if it doesn't contains && immutable
    int indexOf(Object element);
    //Pred: element != null
    //Post: max i (where i 0...size'): elements'[i] equals element or -1 if it doesn't contains && immutable
    int lastIndexOf(Object element);
}
