package com.chatar.gedcom.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.chatar.gedcom.exception.ExceptionThrow;

public class XmlUtils {

	public static Document instanceOf() {
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException exception) {
			throw new RuntimeException(exception);
		}
		return document;
	}

	public static void writeDocumentToFile(Document document, File outputFile) {
		ExceptionThrow.runTimeException("Document or output file name can't be null", document == null, outputFile == null);
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(new DOMSource(document), new StreamResult(outputFile));
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
