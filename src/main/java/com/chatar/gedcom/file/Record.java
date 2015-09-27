package com.chatar.gedcom.file;

import java.util.Arrays;

import com.chatar.gedcom.exception.ExceptionThrow;
import com.chatar.gedcom.util.Assumptions;

public class Record {

	private String[] tokens;
	
	public Record(String lineItem) {
		ExceptionThrow.runTimeException("Line can't be null or empty", lineItem == null || lineItem == "");
		lineItem = lineItem.trim();
		this.tokens = lineItem.split(Assumptions.DELIMITER, 3);
	}
	
	public int getLevel() {
		return Integer.parseInt(tokens[0]);
	}
	
	public String getName() {
		return tokens[1];
	}
	
	public String getValue() {
		if(tokens.length > 2) {
			return tokens[2];
		} return null;
	}

	@Override
	public String toString() {
		return "Record [tokens=" + Arrays.toString(tokens) + "]";
	}
}
