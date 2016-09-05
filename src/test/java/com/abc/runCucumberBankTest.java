package com.abc;

	import org.junit.runner.RunWith; 
	import cucumber.junit.Cucumber; 

	@RunWith(Cucumber.class) 
	@Cucumber.Options(
			monochrome = true,
			format = "pretty")
	public class runCucumberBankTest { }

