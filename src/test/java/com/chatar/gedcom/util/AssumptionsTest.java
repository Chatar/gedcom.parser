package com.chatar.gedcom.util;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AssumptionsTest {

	@Test
	public void testAllAssumptions() {
		assertThat(Assumptions.DELIMITER, equalTo(" "));
		assertThat(Assumptions.FIRST_LEVEL, equalTo(0));
		assertThat(Assumptions.ROOT, equalTo("gedcom"));
		assertThat(Assumptions.XML_FILE_NAME, equalTo("actual_gedcom_parser.xml"));
	}
}
