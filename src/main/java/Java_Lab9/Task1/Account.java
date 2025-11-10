package Java_Lab9.Task1;

import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private static final AtomicLong idCounter = new AtomicLong(0);

    private final long id;
    private int balance;

    public Account(int initialBalance) {
        this.balance = initialBalance;
        this.id = idCounter.incrementAndGet();
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {

        balance += amount;
    }

    public synchronized int getBalance() {

        return balance;
    }

    public long getId() {

        return id;
    }

    @Override
    public String toString() {
        return "Account #" + id + " (Balance: " + balance + ")";
    }
}