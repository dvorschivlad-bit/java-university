package org.example;

import org.example.exceptions.InvalidAmountException;
import org.example.exceptions.InsufficientFundsException;

public class SavingsAccount extends BankAccount {

    private double interestRate;
    private static final double MIN_BALANCE = 100.0;

    public SavingsAccount(String owner, double initialBalance, double interestRate) throws InvalidAmountException {
        super(owner, initialBalance);
        if (interestRate < 0) throw new InvalidAmountException("Interest rate cannot be negative.");
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        try {
            deposit(interest);
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (getBalance() - amount < MIN_BALANCE)
            throw new InsufficientFundsException("Savings account must keep a minimum balance of " + MIN_BALANCE);
        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return "SavingsAccount{owner='" + getOwner() + "', balance=" + getBalance() + ", interestRate=" + interestRate + "%}";
    }
}