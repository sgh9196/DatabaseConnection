package org.mybatis.domain;

public class User {
	
	private String userName;
	private String passWD;
	private String dbName;
	
	public User() {}
	
	public User(String userName ,String passWD, String dbName) {
		this.userName = userName;
		this.passWD = passWD;
		this.dbName = dbName;
	}
	
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	public String getPassWD() { return passWD; }
	public void setPassWD(String passWD) { this.passWD = passWD; }
	
	public String getDbName() { return dbName; }
	public void setDbName(String dbName) { this.dbName = dbName; }
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", passWD=" + passWD + ", dbName=" + dbName + "]";
	}
	
}
