package org.example;

import org.example.exceptions.InvalidAmountException;
import org.example.exceptions.InsufficientFundsException;

public class Main {

    public static void main(String[] args) {
        try {
            BankAccount account = new BankAccount("Alice", 500);
            System.out.println(account);

            account.deposit(200);
            System.out.println("After deposit: " + account.getBalance());

            account.withdraw(100);
            System.out.println("After withdrawal: " + account.getBalance());

            SavingsAccount savings = new SavingsAccount("Bob", 1000, 5);
            System.out.println(savings);

            savings.applyInterest();
            System.out.println("After interest: " + savings.getBalance());

            savings.withdraw(800);
            System.out.println("After withdrawal: " + savings.getBalance());

        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            BankAccount account = new BankAccount("Charlie", 200);
            account.withdraw(500);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            BankAccount account = new BankAccount("Diana", 100);
            account.deposit(-50);
        } catch (InvalidAmountException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            SavingsAccount savings = new SavingsAccount("Eve", 200, 3);
            savings.withdraw(150);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}