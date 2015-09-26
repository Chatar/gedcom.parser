package com.chatar.gedcom.main;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.chatar.gedcom.builder.impl.GedcomTreeBuilder;
import com.chatar.gedcom.builder.impl.XmlBuilder;
import com.chatar.gedcom.file.GedcomFileParser;

public class EndToEndTest extends XMLTestCase {

	private static final String INPUT_FILE = "GEDCOM Parser Challenge sample data.txt";
	private static final String OUTPUT_FILE = "Actual_GEDCOM Parser Challenge sample data.xml";
	private static final String EXPECTED_OUTPUT_FILE = "Expected_GEDCOM Parser Challenge sample data.xml";
	
	@Test
	public void testShouldAbleToConvertGivenGedcomFileToXml() throws IOException, SAXException {
		String outputPath = new XmlBuilder(OUTPUT_FILE).buildFrom(new GedcomTreeBuilder().build(new GedcomFileParser().parse(INPUT_FILE)));
		File outputFile = new File(OUTPUT_FILE);
		File expectedOutputFile = new File(EXPECTED_OUTPUT_FILE);
		
		assertThat(outputPath, equalTo(new File(OUTPUT_FILE).getAbsolutePath()));

		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setIgnoreAttributeOrder(true);
		
		String actualXML = FileUtils.readFileToString(outputFile);
		String expectedXML = FileUtils.readFileToString(expectedOutputFile);
		
		DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));

        List<?> allDifferences = diff.getAllDifferences();
        Assert.assertEquals("Differences found: "+ diff.toString(), 0, allDifferences.size());
	}
}
