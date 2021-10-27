package com.core;

/**
 * SQL 실행시 바인딩할 데이터 담는 클래스
 * 
 */
public class DBField {
	
	private String dataType; // String, Integer, Double ...
	private String value; // 바인딩할 데이터
	
	public DBField(String type, String value) {
		this.dataType = type;
		this.value = value;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
