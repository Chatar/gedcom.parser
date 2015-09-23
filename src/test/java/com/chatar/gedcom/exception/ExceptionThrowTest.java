package com.chatar.gedcom.exception;

import org.junit.Test;

public class ExceptionThrowTest {

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionIfRequired() {
		ExceptionThrow.runTimeException("Any message", true);
	}
	
	@Test
	public void shouldNotThrowExceptionIfNotRequired() {
		ExceptionThrow.runTimeException("Any message", false);
	}
}
