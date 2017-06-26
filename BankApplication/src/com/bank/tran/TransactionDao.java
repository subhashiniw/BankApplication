package com.bank.tran;
 
import org.springframework.jdbc.core.support.JdbcDaoSupport;
 
public class TransactionDao extends JdbcDaoSupport{
 
	String name;
	
	public TransactionDao(){}
	
	public TransactionDao(String name) {
		super();
		this.name = name;
	}

	public void insert(Transaction transaction){
		String insertSql ="INSERT INTO TRANSACTIONS (ACCOUNT_NUMBER, TRANSACTION_TIME, OPERATION, AMOUNT) VALUES(?, ?, ?, ?);";
		getJdbcTemplate().update(insertSql,new Object[]{transaction.getAccNo(), transaction.getTransTime(), transaction.getTransType(), transaction.getAmount()});
	}
  
}