package Java_Lab9.Task2;

import java.util.Random;

public class Producer implements Runnable {

    private final CircularBuffer buffer;
    private final int id;
    private final Random rand = new Random();

    public Producer(CircularBuffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            int messageCount = 0;
            while (true) {
                String message = "Потік №" + id + " згенерував повідомлення " + (++messageCount);
                buffer.put(message);

                Thread.sleep(rand.nextInt(100));
            }
        } catch (InterruptedException e) {
        }
    }
}