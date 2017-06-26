package com.bank.acc; 

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.bank.tran.Transaction;
import com.bank.tran.TransactionDao;
public class AccountService {
	
	private static final String CREDIT = "C";
	private static final String DEBIT = "D";	
		
	private Double serviceCharge = 2d;// don't change this
	private AccountDao accountDao;
	private TransactionDao transactionDao;

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}
	 
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	 
	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public void create(Account account) {
		getAccountDao().insert(account);
	}
	@Transactional(rollbackFor=Exception.class)
	public void transferFunds(final Account fromAccount, final Account toAccount, final Double transferAmount) throws Exception{
		toAccount.credit(transferAmount);
		getAccountDao().update(toAccount);
		fromAccount.debit(transferAmount);
		addServiceCharge(fromAccount);
		getTransactionDao().insert(new Transaction(fromAccount.getNumber(), new Date(), DEBIT, transferAmount + serviceCharge));
		getTransactionDao().insert(new Transaction(toAccount.getNumber(), new Date(), CREDIT, transferAmount));
		
		//System.out.println("Impliment Money Transfer!");
	}
 
	public void createAccount(Account account) {
		getAccountDao().insert(account);
	}
	 
	public Account getAccount(String accountNumber) {
		return getAccountDao().select(accountNumber);
	}
	
	private void addServiceCharge(Account account) throws Exception{
		account.debit(serviceCharge);
		if(account.getBalance() < 0 ){
			throw new Exception("Error: Account Balance is not sufficiant.");
		}else{
			getAccountDao().update(account);
		}
	}
	
}