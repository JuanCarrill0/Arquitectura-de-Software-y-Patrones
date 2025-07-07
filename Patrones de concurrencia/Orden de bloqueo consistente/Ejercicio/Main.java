package Ejercicio;

public class Main {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        
        Account accountA = new Account("A123", 1000, manager);
        Account accountB = new Account("B456", 1000, manager);
        
        // Transferencia en ambas direcciones
        accountA.initiateTransfer(accountB, 100);  // A → B
        accountB.initiateTransfer(accountA, 50);   // B → A
    }
}