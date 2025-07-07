package Ejercicio;

// Clase Account básica
public class Account extends Thread {
    private String accountNumber;
    private double balance;
    private AccountManager accountManager;
    private Account targetAccount;
    private double amount;

    public Account(String accountNumber, double initialBalance, AccountManager manager) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.accountManager = manager;
    }

    public void initiateTransfer(Account target, double amt) {
        this.targetAccount = target;
        this.amount = amt;
        this.start();
    }

    @Override
    public void run() {
        accountManager.transfer(this, targetAccount, amount);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new RuntimeException("Fondos insuficientes");
        }
    }
}