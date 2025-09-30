import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) {
        Queue<Integer> queue = new java.util.LinkedList<>();

        // Enqueue elements into the queue
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println("Queue after enqueues: " + queue);

        // Dequeue elements from the queue
        int dequeuedElement = queue.poll();
        System.out.println("Dequeued element: " + dequeuedElement);
        System.out.println("Queue after dequeue: " + queue);

        // Peek at the front element
        int frontElement = queue.peek();
        System.out.println("Front element: " + frontElement);
        System.out.println("Queue after peek: " + queue);

        // Check if the queue is empty
        boolean isEmpty = queue.isEmpty();
        System.out.println("Is the queue empty? " + isEmpty);

        // Get the size of the queue
        int size = queue.size();
        System.out.println("Size of the queue: " + size);
    }
}
