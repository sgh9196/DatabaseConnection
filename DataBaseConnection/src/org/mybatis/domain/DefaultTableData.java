package org.mybatis.domain;

public class DefaultTableData {
	
	private String columnName;
	private ColumnDataType dataType;
	private boolean primaryKey;
	
	public DefaultTableData(String columnName, ColumnDataType dataType, boolean primaryKey) {
		this.columnName = columnName;
		this.dataType = dataType;
		this.primaryKey = primaryKey;
	}
	public String getColumnName() { return columnName; }
	public void setColumnName(String columnName) { this.columnName = columnName; }
	
	public ColumnDataType getDataType() { return dataType; }
	public void setDataType(ColumnDataType dataType) { this.dataType = dataType; }
	
	public boolean isPrimaryKey() { return primaryKey; }
	public void setPrimaryKey(boolean primaryKey) { this.primaryKey = primaryKey; }
	
	@Override
	public String toString() {
		return "DefaultTableData [columnName=" + columnName + ", dataType=" + dataType + ", primaryKey=" + primaryKey
				+ "]";
	}
	
}
