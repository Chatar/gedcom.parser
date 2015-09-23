package com.chatar.gedcom.exception;

public class ExceptionThrow {

	public static void runTimeException(String message, boolean needToThrow) {
		if(needToThrow) {
			throw new RuntimeException(message);
		}
	}

}
