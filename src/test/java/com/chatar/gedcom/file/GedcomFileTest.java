package com.chatar.gedcom.file;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

public class GedcomFileTest {

	@Test(expected = RuntimeException.class)
	public void throwExceptionIfFileNameIsNull() throws IOException {
		GedcomFile gedcomFile = new GedcomFile();
		gedcomFile.loadFrom(null);
	}

	@Test
	public void numberOfRecordsAreExpected() throws IOException {
		GedcomFile gedcomFile = new GedcomFile();
		gedcomFile.loadFrom("GEDCOM Parser Challenge sample data.txt");

		assertThat(gedcomFile.records(), equalTo(518));
	}
}
