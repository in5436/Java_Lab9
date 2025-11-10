package Java_Lab9.Task2;

public class Main {

    private static final int BUFFER_1_SIZE = 10;
    private static final int BUFFER_2_SIZE = 10;

    public static void main(String[] args) {

        CircularBuffer buffer1 = new CircularBuffer(BUFFER_1_SIZE);
        CircularBuffer buffer2 = new CircularBuffer(BUFFER_2_SIZE);

        for (int i = 1; i <= 5; i++) {
            Thread producerThread = new Thread(new Producer(buffer1, i));
            producerThread.setDaemon(true);
            producerThread.start();
        }

        for (int i = 1; i <= 2; i++) {
            Thread transformerThread = new Thread(new Transformer(buffer1, buffer2, i));
            transformerThread.setDaemon(true);
            transformerThread.start();
        }

        System.out.println("Головний потік починає читати 100 повідомлень...");
        try {
            for (int i = 0; i < 100; i++) {
                String finalMessage = buffer2.get();
                System.out.println("ГОЛОВНИЙ: " + finalMessage);
            }
        } catch (InterruptedException e) {
            System.err.println("Головний потік перервано!");
        }

        System.out.println("Головний потік вичитав 100 повідомлень і завершує роботу.");
    }
}
