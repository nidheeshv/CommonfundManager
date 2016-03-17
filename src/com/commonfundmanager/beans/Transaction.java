package com.commonfundmanager.beans;

public class Transaction {

private int tid;
private String tDate;
private String transactionType;
private String category;
private String user;
private String description;
private double amount;
private String approvedStatus;
private String approver;

public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public int getTid() {
	return tid;
}
public void setTid(int tid) {
	this.tid = tid;
}
public String gettDate() {
	return tDate;
}
public void settDate(String tDate) {
	this.tDate = tDate;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getApproved() {
	return approvedStatus;
}
public void setApproved(String approved) {
	this.approvedStatus = approved;
}


public String getApprovedStatus() {
	return approvedStatus;
}
public void setApprovedStatus(String approvedStatus) {
	this.approvedStatus = approvedStatus;
}
public String getApprover() {
	return approver;
}
public void setApprover(String approver) {
	this.approver = approver;
}
public Transaction(int tid, String tDate, String transactionType, String user,
		String description, double amount, String approved) {
	super();
	this.tid = tid;
	this.tDate = tDate;
	this.transactionType = transactionType;
	this.user = user;
	this.description = description;
	this.amount = amount;
	this.approvedStatus = approved;
}
public Transaction() {
	super();
}

public Transaction(String user, String tDate, String transactionType,
		String category, String description, double amount) {
	super();
	this.user = user;
	this.tDate = tDate;
	this.transactionType = transactionType;
	this.category = category;
	this.description = description;
	this.amount = amount;
}

public Transaction(int tid, String user, String tDate, String transactionType,
		String category, String description, double amount) {
	super();
	this.tid = tid;
	this.user = user;
	this.tDate = tDate;
	this.transactionType = transactionType;
	this.category = category;
	this.description = description;
	this.amount = amount;
}



}
