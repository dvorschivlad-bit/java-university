package org.example;

import org.example.exceptions.InvalidAmountException;
import org.example.exceptions.InsufficientFundsException;

public class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) throws InvalidAmountException {
        if (initialBalance < 0) throw new InvalidAmountException("Initial balance cannot be negative.");
        this.owner = owner;
        this.balance = initialBalance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive.");
        balance += amount;
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive.");
        if (amount > balance) throw new InsufficientFundsException("Insufficient funds. Balance: " + balance);
        balance -= amount;
    }

    public String getOwner()  { return owner; }
    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return "BankAccount{owner='" + owner + "', balance=" + balance + "}";
    }
}