package org.mybatis.domain;

public enum DataTypeList {

	CHAR("c", "Char"), VARCHAR("v", "VarChar"), INT("i", "Int"), DATE("d", "Date");

	private String code;
	private String type;

	private DataTypeList(String code, String type) {
		this.code = code;
		this.type = type;
	}

	public String getType() { return type; }
	public String getCode() { return code; }

	public static DataTypeList getByCode(String typeCode) {
		for (DataTypeList g : DataTypeList.values()) {
			if (g.code.equals(typeCode)) {
				return g;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.type;
	}

}
