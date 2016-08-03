package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Wayne Lee
 *
 */
public class SavingsAccount extends Account {
	private double interestRate1000=0;
	//private double interestRate2000=0;
	private double interestRate_General=0;

	public SavingsAccount() {
		super(1);
		interestRate1000=0.01;
		interestRate_General=0.02;
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
                if (datedBalance <= 1000)
                {
            		datedBalance*=(1+interestRate1000/365);	
               }
                else
                {
            		double b1000=1000*(1+interestRate1000/365);	
            		double bgeneral=(datedBalance-1000)*
            				(1+interestRate/365);
            		datedBalance=b1000+bgeneral;
                }
            } // for interestDays
        	datedBalance += t.amount;  
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
            if (datedBalance <= 1000)
            {
        		datedBalance*=(1+interestRate1000/365);	
            }
            else
            {
        		double b1000=1000*(1+interestRate1000/365);	
        		double bgeneral=(datedBalance-1000)*
        				(1+interestRate/365);
        		datedBalance=b1000+bgeneral;
            }
		}//for interestDays

    	return datedBalance;  //current balance				

     }

	@Override
	public String getAccountTypeName() {
		return "Savings Account";
	}
	public double getInterestRate1000() {
		return interestRate1000;
	}

	public void setInterestRate1000(double interestRate1000) {
		this.interestRate1000 = interestRate1000;
	}

	public double getInterestRate_General() {
		return interestRate_General;
	}

	public void setInterestRate_General(double interestRate_General) {
		this.interestRate_General = interestRate_General;
	}

}
