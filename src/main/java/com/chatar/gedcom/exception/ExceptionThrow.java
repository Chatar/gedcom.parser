package com.chatar.gedcom.exception;

public class ExceptionThrow {

	public static void runTimeException(String message, boolean... conditions) {
		for (boolean condition : conditions) {
			if (condition) {
				throw new RuntimeException(message);
			}
		}
	}
}
