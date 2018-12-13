package org.mybatis.domain;

public class CreateTableInfo {
	
	private String columnName;
	private String dataType;
	private boolean primaryKey;
	
	public CreateTableInfo(String columnName, String dataType, boolean primaryKey) {
		this.columnName = columnName;
		this.dataType = dataType;
		this.primaryKey = primaryKey;
	}
	
	public String getColumnName() { return columnName; }
	public void setColumnName(String columnName) { this.columnName = columnName; }
	
	public String getDataType() { return dataType; }
	public void setDataType(String dataType) { this.dataType = dataType; }
	
	public boolean isPrimaryKey() { return primaryKey; }
	public void setPrimaryKey(boolean primaryKey) { this.primaryKey = primaryKey; }
	
	@Override
	public String toString() {
		return "DefaultTableData [columnName=" + columnName + ", dataType=" + dataType + ", primaryKey=" + primaryKey
				+ "]";
	}
	
}