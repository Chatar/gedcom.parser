package com.chatar.gedcom.util;

import java.io.File;

import org.junit.*;
import org.w3c.dom.Document;

public class XmlUtilsTest {

	@Test
	public void shouldAbleToCreateXmlDocument() {
		Assert.assertNotNull(XmlUtils.instanceOf());
	}
	
	@Test(expected = RuntimeException.class)
	public void throwExceptionIfOutputFileIsNull() {
		Document document = XmlUtils.instanceOf();
		XmlUtils.writeDocumentToFile(document, null);
	}
	
	@Test(expected = RuntimeException.class)
	public void throwExceptionIfDocumentIsNull() {
		XmlUtils.writeDocumentToFile(null, new File("tmp.xml"));
	}
	
	@Test
	public void shouldAbleWriteIfFileNameIsCorrect() {
		Document document = XmlUtils.instanceOf();
		XmlUtils.writeDocumentToFile(document, new File("tmp.xml"));
	}
}
