package com.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bank.acc.Account;
import com.bank.acc.AccountService;
 
public class TestTransaction {
	
	private static final String fromAccountNumber = "ACC01";
	private static final String toAccountNumber = "ACC02";
 
	public static void main(String[] args) {
		
		System.out.println("************** BEGINNING PROGRAM **************");
		 
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		AccountService OtherBankAccountService = (AccountService) context.getBean("accountService");
		AccountService OurAccountService = (AccountService) context.getBean("accountService");
		 
		try {
		
			System.out.println("Creating new accounts " + fromAccountNumber + " and " + toAccountNumber);
			Account fromAccount = new Account(fromAccountNumber,100d);// don't change this
			Account toAccount = new Account(toAccountNumber,200d);// don't change this
			OtherBankAccountService.create(fromAccount);
			OtherBankAccountService.create(toAccount);
			printAccountInformation(OtherBankAccountService);
			System.out.println("New accounts created successfully");
			System.out.println("----");
			
			//3rd party transfer 
			Double transferAmount = 50d;// don't change this
			OtherBankAccountService.setServiceCharge(3d);// don't change this
			System.out.println("Transferring " + transferAmount + " from account " + fromAccountNumber + " to account " + toAccountNumber);
			OtherBankAccountService.transferFunds(fromAccount, toAccount, transferAmount);
			printAccountInformation(OtherBankAccountService);
			System.out.println("The amount " + transferAmount + " was transferred successfully");
			System.out.println("----");
			
			// Uncomment this when doing the Task 3
			//Same bank transfer
			transferAmount = 45d;// don't change this
			System.out.println("Transferring " + transferAmount + " from account " + fromAccountNumber + " to account " + toAccountNumber);
			OurAccountService.transferFunds(fromAccount, toAccount, transferAmount);
			printAccountInformation(OurAccountService);
			System.out.println("The amount " + transferAmount + " was transferred successfully");
			System.out.println("----");
		
		} catch (Exception e) {
			System.out.println("ERROR IN TRANSACTION AND COULD NOT BE TRANSFERRED DUE TO EXCEPTION.");
			System.out.println("THE TRANSACTION SHALL BE ROLLED BACK.");
		}
		
		System.out.println("************** ENDING PROGRAM **************");
	}
	 
	private static void printAccountInformation(AccountService accountService){
	 
		Account fromAccount = accountService.getAccount(fromAccountNumber);
		Account toAccount = accountService.getAccount(toAccountNumber);
		System.out.println("Service Charge : "+ accountService.getServiceCharge());
		System.out.println("Balance in account " + fromAccountNumber + " = " + fromAccount.getBalance());
		System.out.println("Balance in account " + toAccountNumber + " = " + toAccount.getBalance());
	}
}