package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }
    
    /*
     * Transfer Money from this Account 
     * to another account
     * @param fromAccount:  source Account
     * @param toAccount : destination account
     * @param amount : The amount to transfer
     * 
     */
        
    public void transfer(Account fromAccount,double amount, Account toAccount){
        	/*
        	 * only allow transfer between own account
        	 */
    	double total=fromAccount.sumTransactions();
    	if (!accounts.contains(fromAccount)){
            throw new IllegalArgumentException("Invalid Source Account to transfer");	
    	}
    	else if (!accounts.contains(toAccount)){
            throw new IllegalArgumentException("Invalid Destination Account to transfer");	
    	}
    	else if (amount<0) {
                throw new IllegalArgumentException("amount must be greater than zero");
        }else if (total<amount) {
                throw new IllegalArgumentException("amount must be NOT greater than total");
        }else {
            	fromAccount.withdraw(amount);
            	toAccount.deposit(amount);       		
        }
    }
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";
        /*
       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }
        */
        s +=a.getAccountTypeName()+"\n";
        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
