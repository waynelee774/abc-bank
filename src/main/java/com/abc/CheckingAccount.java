/**
 * 
 */
package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Wayne Lee
 *
 */
public class CheckingAccount extends Account {
	//double interestRate1000=0;
	//double interestRate2000=0;
	private double interestRate_General=0;

	/**
	 * @param accountType
	 */
	public CheckingAccount() {
		super(0);
		interestRate_General=0.001;
	}

	@Override
	public double interestEarned() {
		return accountCurrentBalance()-sumTransactions();
	}


	@Override
	public double accountCurrentBalance() {
    	double datedBalance=0.0;//total balance
    	//adjust rate based on balance for checking account
    	double interestRate=interestRate_General;
    	Date balanceDate=transactions.
    			get(0).getTransactionDate();
    	Date TransactionDate;
    	int interestDays;
    	//Collections.sort(transactions);
    	//Calculate accumulated balance ( amount+interest) at each transaction
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
        		datedBalance*=(1+interestRate/365);
        		}
        	datedBalance += t.amount;  
        	balanceDate=t.getTransactionDate();

        }
    	TransactionDate=
    			Calendar.getInstance().getTime();
    	//get interest days at Now  from previous balance
    	interestDays=(int)((TransactionDate.getTime()
    					-balanceDate.getTime())
    							/1000/60/60/24); 
    	for (int d=0;d<interestDays;d++)
    		{datedBalance*=(1+interestRate/365);}
    	return datedBalance;  //current balance		return 0;
	}

	@Override
	public String getAccountTypeName() {
		return "Checking Account";
	}
	

	public double getInterestRate_General() {
		return interestRate_General;
	}

	public void setInterestRate_General(double interestRate_General) {
		this.interestRate_General = interestRate_General;
	}


}
