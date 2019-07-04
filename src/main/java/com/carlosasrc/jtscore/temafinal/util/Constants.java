package com.carlosasrc.jtscore.temafinal.util;

public enum Constants {
	OUTPUT_FILE_NAME("data.out.dat"),
	FILE_NAME_VALIDATOR("[a-zA-Z0-9]+.dat"),
	DATA_LINE_VALIDATOR("\n|(?=\\s[0-9]{3})"),
	LIST_ITEM_DELIMITER("\\]|\\[|,|-");
	
	
	public String value;
	private Constants(String value) {
        this.value = value;
    }
}
