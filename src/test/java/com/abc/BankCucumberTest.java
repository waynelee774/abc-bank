package com.abc;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.When;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.But;

public class BankCucumberTest{
	Bank myBank;
	@Given("^I open a bank$")
	public void openBankStep() {
		myBank=new Bank();
		System.out.println("myBank openned");
	}
	
	@When ("^I add user name as \"(.*)\"$") 
	public void addCustomerStep(String userName) {
		Customer c=new Customer(userName);
		myBank.addCustomer(c);
	}

	@Then ("^customer should be added as \"(.*)\"$")
	public void verifyCustomerStep(String userName) {
		if(myBank.findCustomer(userName))
			{System.out.println("testVerifyCustomer Pass");}
		else
			{System.out.println("testVerifyCustomer Failed");}
	}
	
	@When ("^I open an account for \"(.*)\"$") 
	public void openAccountStep(String userName) {
		Customer c=myBank.getCustomer(userName);
		if (c!=null) {
			c.openAccount(new CheckingAccount());
		} 
	}
	
	@Then ("^verify added account for \"(.*)\"$")
	public void verifyAccountStep(String userName)  {
		Customer c=myBank.getCustomer(userName);
		if (c!=null) {
			if (c.getNumberOfAccounts()!=0) {
				System.out.println("testVerifyAccount Pass");
				return;
			}
		}
		System.out.println("testVerifyAccount Failed");
	}
	
	@But ("^customerSummary should be available$")
	public void displayCustomerSummaryStep() {
		System.out.println(myBank.customerSummary());
	}
}
