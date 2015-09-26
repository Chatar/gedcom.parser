package com.chatar.gedcom.builder.impl;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.chatar.gedcom.builder.FileBuilder;
import com.chatar.gedcom.tree.EntityTree;
import com.chatar.gedcom.tree.MockEntities;
import com.chatar.gedcom.util.Assumptions;

public class XmlBuilderTest {

	private static final String PATH = "/Users/chatar/Documents/dev/projects/weekend-hiring3/gedcom.parser/src/test/java/com/chatar/gedcom/builder/impl/";
	private static final String EXPECTED_XML_PATH = PATH + "actual_gedcom_parser.xml"; 
			
	@Test(expected = RuntimeException.class)
	public void test() {
		FileBuilder xmlBuilder = new XmlBuilder(PATH + Assumptions.XML_FILE_NAME);
		xmlBuilder.buildFrom(null);
	}

	@Test
	public void xmlIsAsExpected() throws IOException {
		XmlBuilder xmlBuilder = new XmlBuilder(PATH + Assumptions.XML_FILE_NAME);
		String actual = xmlBuilder.buildFrom(entityTree());
		
		assertThat(actual, equalTo(PATH + Assumptions.XML_FILE_NAME));
		assertTrue(FileUtils.contentEquals(new File(actual), new File(EXPECTED_XML_PATH)));
	}

	private EntityTree entityTree() {
		return new GedcomTreeBuilder().build(MockEntities.build());
	}
}
