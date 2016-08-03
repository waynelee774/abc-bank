package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class AccountTest {

	@Test
	public void CheckingAccountTest() {
	Account account=new CheckingAccount();
	Date currentTime=Calendar.getInstance().getTime();
	long curentMS=currentTime.getTime();
	Date previousTime1=new Date();
	Date previousTime2=new Date();
	Date previousTime3=new Date();
	previousTime1.setTime(curentMS-(long)1000*60*60*24*60);//100 days ago
	account.deposit(1000, previousTime1);
	previousTime2.setTime(curentMS-(long)1000*60*60*24*30);//50 days ago
	account.deposit(2000, previousTime2);
	previousTime3.setTime(curentMS-(long)1000*60*60*24*15);//30 days ago
	account.withdraw(1000, previousTime3); 
	// verify now
	double accountBalance=
			account.accountCurrentBalance();
	System.out.print("Checking: "+accountBalance+"\n\n");
    assertEquals(2000.2876902616972, accountBalance,0.0001);

	}
	

	@Test
	public void SavingsAccountTest() {
	Account account=new SavingsAccount();
	Date currentTime=Calendar.getInstance().getTime();
	long curentMS=currentTime.getTime();
	Date previousTime1=new Date();
	Date previousTime2=new Date();
	Date previousTime3=new Date();
	previousTime1.setTime(curentMS-(long)1000*60*60*24*60);//100 days ago
	account.deposit(1000, previousTime1);
	previousTime2.setTime(curentMS-(long)1000*60*60*24*30);//50 days ago
	account.deposit(2000, previousTime2);
	previousTime3.setTime(curentMS-(long)1000*60*60*24*15);//30 days ago
	account.withdraw(1000, previousTime3); 
	// verify now
	double accountBalance=
			account.accountCurrentBalance();
	System.out.print("Saving:"+accountBalance+"\n\n");
    assertEquals(2004.114547144844, accountBalance,0.00001);
	}
	

	@SuppressWarnings("deprecation")
	@Test
	public void Maxi_SavingsAccountTest() {
	Account account=new Maxi_SavingsAccount();
	Date currentTime=Calendar.getInstance().getTime();
	long curentMS=currentTime.getTime();
	Date previousTime1=new Date();
	Date previousTime2=new Date();
	Date previousTime3=new Date();
	previousTime1.setTime(curentMS-(long)1000*60*60*24*60);//100 days ago
	account.deposit(1000, previousTime1);
	previousTime2.setTime(curentMS-(long)1000*60*60*24*30);//50 days ago
	account.deposit(2000, previousTime2);
	previousTime3.setTime(curentMS-(long)1000*60*60*24*15);//30 days ago
	account.withdraw(1000, previousTime3); 
	// verify now
	double accountBalance=
			account.accountCurrentBalance();
	System.out.print("Maxi_Saving:"+accountBalance+"\n\n");
    assertEquals(2011.7289375190528, accountBalance,0.0001);

	}
}
