package Java_Lab9.Task2;

import java.util.Random;

public class Transformer implements Runnable {

    private final CircularBuffer bufferFrom;
    private final CircularBuffer bufferTo;
    private final int id;
    private final Random rand = new Random();

    public Transformer(CircularBuffer bufferFrom, CircularBuffer bufferTo, int id) {
        this.bufferFrom = bufferFrom;
        this.bufferTo = bufferTo;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String originalMessage = bufferFrom.get();

                String messageContent = originalMessage.split("повідомлення ")[1];

                String newMessage = "Потік №" + id + " переклав повідомлення " + messageContent;

                bufferTo.put(newMessage);

                Thread.sleep(rand.nextInt(100));
            }
        } catch (InterruptedException e) {
        }
    }
}
