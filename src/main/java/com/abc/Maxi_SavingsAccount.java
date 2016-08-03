package com.abc;

import java.util.Calendar;
import java.util.Date;
/**
 * @author Wayne Lee
 *
 */
public class Maxi_SavingsAccount extends Account {
	private double interestRate10dayWithDraw=0;
	private double interestRate_General=0;

	public Maxi_SavingsAccount() {
		super(2);
		interestRate10dayWithDraw=0.001;
		interestRate_General=0.05;	}

	@Override
	public double interestEarned() {
		return accountCurrentBalance()-sumTransactions();
	}

	@Override
	public double accountCurrentBalance() {
    	double datedBalance=0.0;//total balance
    	//adjust rate based on balance for checking account
    	double interestRate=interestRate_General;
    	double previousTransactionAmount=0;
    	Date balanceDate=transactions.
    			get(0).getTransactionDate();
    	Date TransactionDate;
    	int interestDays;
    	//Collections.sort(transactions);
    	//Calculate accumulated balance ( amount+interest) at time of each transaction
        for (Transaction t: transactions)
        {
        	TransactionDate=t.getTransactionDate();
        	//get interest days at transaction event from previous balance
        	interestDays=(int)((TransactionDate.getTime()
        					-balanceDate.getTime())
        						/1000/60/60/24); 
        	//assumption is made that transaction are in historical order
        	// so that interestDays>=0, otherwise need to sort the list
        	for (int d=0;d<interestDays;d++)
        	{
        	if (previousTransactionAmount<0 && d<10){
        		// withdrawn is last 10 days
        		interestRate=interestRate10dayWithDraw;
        	}else {
            	interestRate=interestRate_General;
        	}
            datedBalance*=(1+interestRate/365);
        	} // for interestDays
        	datedBalance += t.amount;  
        	previousTransactionAmount=t.amount;
        	balanceDate=t.getTransactionDate();
    	}//  for transactions
        
    	TransactionDate=
    			Calendar.getInstance().getTime();
    	//get interest days at Now  from Last previous balance
    	interestDays=(int)((TransactionDate.getTime()
    					-balanceDate.getTime())
    							/1000/60/60/24); 
    	for (int d=0;d<interestDays;d++)
		{
        	if (previousTransactionAmount<0 && d<10){
        		// withdrawn is last 10 days
        		interestRate=interestRate10dayWithDraw;
        	}else {
            	interestRate=interestRate_General;
        	}
            datedBalance*=(1+interestRate/365);	 

		}//for interestDays

    	return datedBalance;  //current balance				

	}



	@Override
	public String getAccountTypeName() {
		// TODO Auto-generated method stub
		return "Maxi Savings Account";
	}

}
