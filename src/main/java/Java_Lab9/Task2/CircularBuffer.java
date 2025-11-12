package Java_Lab9.Task2;

public class CircularBuffer {

    private final String[] buffer;
    private final int capacity;

    private int head = 0;
    private int tail = 0;
    private int currentSize = 0;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new String[capacity];
    }

    private boolean isFull() {
        return currentSize == capacity;
    }

    private boolean isEmpty() {
        return currentSize == 0;
    }

    public synchronized void put(String message) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        buffer[tail] = message;
        tail = (tail + 1) % capacity;
        currentSize++;

        notifyAll();
    }

    public synchronized String get() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        String message = buffer[head];
        head = (head + 1) % capacity;
        currentSize--;

        notifyAll();

        return message;
    }
}