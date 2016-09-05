Feature: bank
#this is background test for bank scenarios
#effort is made only to show some usages of cucumber
#instead of complete set of test scenarios 

Background:
	User opens bank 
	Given I open a bank

#Scenario with customerSummary
Scenario: add user and accounts
	When I add user name as "John"
	Then customer should be added as "John"


#Scenario AND
	When I add user name as "Bill"
	And I add user name as "Smith"
	Then customer should be added as "Bill"
	And customer should be added as "Smith"
	
#Scenario open a checking account
	When I open an account for "John"
	Then verify added account for "John"
	
#Scenario But
	When I open an account for "NoExist"
	Then verify added account for "NoExist"
	But customerSummary should be available

#Scenario batched test
Scenario Outline:	test multiple users
	When I add user name as "<userName>"
	And I open an account for "<userAccount>"
	Then verify added account for "<userToVerify>"
	But customerSummary should be available
Examples:
	|userName|userAccount|userToVerify|
	|John|John|John|
	|Bill|Bill|Bill|
	|Wayne|Wayne|Wayne|