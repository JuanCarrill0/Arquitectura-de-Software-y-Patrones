package Ejercicio;

public class AccountManager {
    public void transfer(Account fromAccount, Account toAccount, double amount) {
        // Orden de bloqueo consistente basado en número de cuenta
        Account firstLock, secondLock;
        
        if (fromAccount.getAccountNumber().compareTo(toAccount.getAccountNumber()) < 0) {
            firstLock = fromAccount;
            secondLock = toAccount;
        } else {
            firstLock = toAccount;
            secondLock = fromAccount;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {
                System.out.println("Transferencia iniciada: " + amount + " de " + fromAccount.getAccountNumber() + " a " + toAccount.getAccountNumber());
                
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                
                System.out.println("Transferencia completada. Nuevos saldos:");
                System.out.println(fromAccount.getAccountNumber() + ": " + fromAccount.getBalance());
                System.out.println(toAccount.getAccountNumber() + ": " + toAccount.getBalance());
            }
        }
    }
}
