package Java_Lab9.Task1;

public class Bank {

    public void transfer(Account from, Account to, int amount) {
        if (from.getId() == to.getId()) {
            return;
        }

        Account lock1 = (from.getId() < to.getId()) ? from : to;
        Account lock2 = (from.getId() < to.getId()) ? to : from;

        synchronized (lock1) {
            synchronized (lock2) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }

    public long getTotalBalance(Account[] accounts) {
        long total = 0;
        for (Account acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }
}
