package Java_Lab9.Task1;

import java.util.Random;

public class Main {

    private static final int NUM_ACCOUNTS = 100;
    private static final int NUM_THREADS = 2000;
    private static final int MAX_INITIAL_BALANCE = 10000;
    private static final int MAX_TRANSFER_AMOUNT = 100;

    public static void main(String[] args) {

        Bank bank = new Bank();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        Random rand = new Random();

        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts[i] = new Account(rand.nextInt(MAX_INITIAL_BALANCE));
        }

        long sumBefore = bank.getTotalBalance(accounts);
        System.out.println("Загальний баланс ДО переказів: " + sumBefore);
        System.out.println("Запускаємо " + NUM_THREADS + " потоків...");

        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {

            Runnable transferTask = () -> {
                Account from = accounts[rand.nextInt(NUM_ACCOUNTS)];
                Account to;
                do {
                    to = accounts[rand.nextInt(NUM_ACCOUNTS)];
                } while (from.getId() == to.getId());

                int amount = rand.nextInt(MAX_TRANSFER_AMOUNT);

                bank.transfer(from, to, amount);
            };

            threads[i] = new Thread(transferTask);
            threads[i].start();
        }

        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("...Всі потоки завершили роботу.");
        long sumAfter = bank.getTotalBalance(accounts);
        System.out.println("Загальний баланс ПІСЛЯ переказів: " + sumAfter);

        if (sumBefore == sumAfter) {
            System.out.println("\nУСПІХ! Баланс банку зберігся!");
        } else {
            System.err.println("\nПОМИЛКА! Баланс банку змінився!");
            System.err.println("Різниця: " + (sumAfter - sumBefore));
        }
    }
}