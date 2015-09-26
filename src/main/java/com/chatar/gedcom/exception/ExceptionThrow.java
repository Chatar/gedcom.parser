package com.chatar.gedcom.exception;

public class ExceptionThrow {

	public static void runTimeException(String message, boolean... failureConditions) {
		for (boolean failureCondition : failureConditions) {
			if (failureCondition) {
				throw new RuntimeException(message);
			}
		}
	}
}
