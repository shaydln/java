package queue;
import java.util.Arrays;
import java.util.Random;

import static queue.ArrayQueueModule.*;
import static queue.ArrayQueueADT.*;

public class ArrayQueueMyTests {
    public static void main(String[] args) {
        //TESTS FOR ArrayQueueModule
        System.out.println("TESTS FOR ArrayQueueModule\n--------------------------");
        Random random = new Random();
        int numberOfValue = 1;
        for (int i = 0; i < 15; i++) {
            int cmd = random.nextInt(10);
            switch (cmd) {
                case 1:
                    System.out.println("COMMAND = element");
                    if (isEmpty()) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("First element is queue is " + element());
                    }
                    break;
                case 2:
                    System.out.println("COMMAND = dequeue");
                    if (isEmpty()) {
                        System.out.println("Nothing to delete");
                    } else {
                        System.out.println("We deleted : " + dequeue());
                    }
                    break;
                case 3:
                    System.out.println("COMMAND = size\nSize of queue is " + size());
                    break;
                case 4:
                    System.out.println("COMMAND = isEmpty\n" + (isEmpty() ? "Queue is empty" : "Queue is not empty"));
                    break;
                case 5:
                    clear();
                    System.out.println("COMMAND = clear\nQueue was cleared");
                    break;
                case 6:
                    System.out.println("COMMAND = toArray\n" + Arrays.toString(toArray()));
                default:
                    enqueue("val" + numberOfValue);
                    System.out.println("COMMAND = enqueue\nAdded element val" + numberOfValue);
                    numberOfValue++;
                    break;
            }
        }

        //TESTS FOR ArrayQueueADT
        System.out.println("\n\n\nTESTS FOR ArrayQueueADT\n--------------------------");
        ArrayQueueADT queueADT = new ArrayQueueADT();
        numberOfValue = 1;
        for (int i = 0; i < 15; i++) {
            int cmd = random.nextInt(10);
            switch (cmd) {
                case 1:
                    System.out.println("COMMAND = element");
                    if (isEmpty(queueADT)) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("First element is queue is " + element(queueADT));
                    }
                    break;
                case 2:
                    System.out.println("COMMAND = dequeue");
                    if (isEmpty(queueADT)) {
                        System.out.println("Nothing to delete");
                    } else {
                        System.out.println("We deleted : " + dequeue(queueADT));
                    }
                    break;
                case 3:
                    System.out.println("COMMAND = size\nSize of queue is " + size(queueADT));
                    break;
                case 4:
                    System.out.println("COMMAND = isEmpty\n" + (isEmpty(queueADT) ? "Queue is empty" : "Queue is not empty"));
                    break;
                case 5:
                    clear(queueADT);
                    System.out.println("COMMAND = clear\n"+"Queue was cleared");
                    break;
                case 6:
                    System.out.println("COMMAND = toArray\n" + Arrays.toString(toArray(queueADT)));
                default:
                    enqueue(queueADT, "val" + numberOfValue);
                    System.out.println("COMMAND = enqueue\nAdded element val" + numberOfValue);
                    numberOfValue++;
                    break;
            }
        }

        //TESTS FOR ArrayQueue
        System.out.println("\n\n\nTESTS FOR ArrayQueue\n--------------------------");
        ArrayQueue queue = new ArrayQueue();
        numberOfValue = 1;
        for (int i = 0; i < 15; i++) {
            int cmd = random.nextInt(10);
            switch (cmd) {
                case 1:
                    System.out.println("COMMAND = element");
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("First element is queue is " + queue.element());
                    }
                    break;
                case 2:
                    System.out.println("COMMAND = dequeue");
                    if (queue.isEmpty()) {
                        System.out.println("Nothing to delete");
                    } else {
                        System.out.println("We deleted : " + queue.dequeue());
                    }
                    break;
                case 3:
                    System.out.println("COMMAND = size\nSize of queue is " + queue.size());
                    break;
                case 4:
                    System.out.println("COMMAND = isEmpty\n" + (queue.isEmpty() ? "Queue is empty" : "Queue is not empty"));
                    break;
                case 5:
                    queue.clear();
                    System.out.println("COMMAND = clear\n"+"Queue was cleared");
                    break;
                case 6:
                    System.out.println("COMMAND = toArray\n" + Arrays.toString(queue.toArray()));
                case 7:
                    System.out.println("COMMAND = IndexOf\n" + queue.indexOf("val" + (numberOfValue-1)));
                case 8:
                    System.out.println("COMMAND = lastIndexOf\n" + queue.lastIndexOf("val" + (numberOfValue-1)));
                default:
                    queue.enqueue( "val" + numberOfValue);
                    System.out.println("COMMAND = enqueue\nAdded element val" + numberOfValue);
                    numberOfValue++;
                    break;
            }
        }
    }
}
