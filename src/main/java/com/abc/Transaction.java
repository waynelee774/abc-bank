package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    //overload function in order to create historic transactions     
    // use this overload with caution, make sure transaction is sorted
    // a safe way is to sort transactions.
    public Transaction(double amount, Date date) {
        this.amount = amount;
        this.transactionDate = date;
    }
    public Date getTransactionDate() {
    	return transactionDate;
    }

}
