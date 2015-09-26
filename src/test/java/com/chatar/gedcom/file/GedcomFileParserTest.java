package com.chatar.gedcom.file;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class GedcomFileParserTest {

	@Test(expected = RuntimeException.class)
	public void throwExceptionIfFileNameIsNull() throws IOException {
		FileParser parser = new GedcomFileParser();
		parser.parse(null);
	}

	@Test
	public void numberOfRecordsAreExpected() throws IOException {
		FileParser parser = new GedcomFileParser();
		List<Entity> entities = parser.parse("GEDCOM Parser Challenge sample data.txt");

		assertThat(entities.size(), equalTo(518));
		
		assertThat(entities.get(0).getRecords().size(), equalTo(12));
	}
}
