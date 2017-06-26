package com.bank;
 
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.sql.DataSource;
 
public class DbUtil {
 
private DataSource dataSource;
 
public DataSource getDataSource() {
	return dataSource;
}
 
public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
}
 
public void initialize(){
	DataSource dataSource = getDataSource();
	try {
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate("IF OBJECT_ID('TRANSACTIONS') IS NULL CREATE TABLE TRANSACTIONS (TRANSACTION_UNO INT IDENTITY(1,1) PRIMARY KEY, ACCOUNT_NUMBER VARCHAR(50), TRANSACTION_TIME datetime, OPERATION CHAR(1), AMOUNT DECIMAL(10,2))");
		statement.executeUpdate("IF OBJECT_ID('ACCOUNT') IS NULL CREATE TABLE ACCOUNT (ACCOUNT_NUMBER VARCHAR(50), BALANCE DECIMAL(10,2))");
		statement.executeUpdate("DELETE FROM ACCOUNT");
		statement.executeUpdate("DELETE FROM TRANSACTIONS");
		statement.close();
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}