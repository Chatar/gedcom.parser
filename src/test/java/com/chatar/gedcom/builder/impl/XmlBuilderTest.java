package com.chatar.gedcom.builder.impl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.chatar.gedcom.builder.FileBuilder;
import com.chatar.gedcom.tree.EntityTree;
import com.chatar.gedcom.tree.MockEntities;
import com.chatar.gedcom.util.Assumptions;

public class XmlBuilderTest {

	private static final String PATH = "src/test/resources/";
	private static final String EXPECTED_XML_PATH = PATH + "actual_gedcom_parser.xml"; 
			
	@Test(expected = RuntimeException.class)
	public void throwExceptionIfOutputFileIsNull() {
		FileBuilder xmlBuilder = new XmlBuilder(PATH + Assumptions.XML_FILE_NAME);
		xmlBuilder.buildFrom(null);
	}

	@Test
	public void xmlIsAsExpected() throws IOException {
		XmlBuilder xmlBuilder = new XmlBuilder(PATH + Assumptions.XML_FILE_NAME);
		String actual = xmlBuilder.buildFrom(entityTree());
		
		assertThat(new File(actual).exists(), is(true));
		assertTrue(FileUtils.contentEquals(new File(actual), new File(EXPECTED_XML_PATH)));
	}

	private EntityTree entityTree() {
		return new GedcomTreeBuilder().build(MockEntities.build());
	}
}
