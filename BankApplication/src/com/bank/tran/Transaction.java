package com.bank.tran;

import java.util.Date;

public class Transaction {
	
	private String accNo;
	private Date transTime;
	private String transType;
	private Double amount;
	
	public Transaction() {
	}	
	public String getAccNo() {
		return accNo;
	}
	public Transaction(String accNo, Date transTime, String transType, Double amount) {
		super();
		this.accNo = accNo;
		this.transTime = transTime;
		this.transType = transType;
		this.amount = amount;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}	

}
