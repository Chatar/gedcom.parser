package com.chatar.gedcom.main;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.chatar.gedcom.builder.impl.GedcomTreeBuilder;
import com.chatar.gedcom.builder.impl.XmlBuilder;
import com.chatar.gedcom.file.GedcomFileParser;

public class EndToEndTest {

	private static final String INPUT_FILE = "GEDCOM Parser Challenge sample data.txt";
	private static final String OUTPUT_FILE = "Actual_GEDCOM Parser Challenge sample data.xml";
	private static final String EXPECTED_OUTPUT_FILE = "Expected_GEDCOM Parser Challenge sample data.xml";
	
	@Test
	public void shouldAbleToConvertGivenGedcomFileToXml() throws IOException {
		String outputPath = new XmlBuilder(OUTPUT_FILE).buildFrom(new GedcomTreeBuilder().build(new GedcomFileParser().parse(INPUT_FILE)));
		
		assertThat(outputPath, equalTo(new File(OUTPUT_FILE).getAbsolutePath()));
		
		assertTrue(FileUtils.contentEquals(new File(OUTPUT_FILE), new File(EXPECTED_OUTPUT_FILE)));
	}
}
