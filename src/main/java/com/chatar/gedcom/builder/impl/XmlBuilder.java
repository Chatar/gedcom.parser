package com.chatar.gedcom.builder.impl;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.chatar.gedcom.builder.FileBuilder;
import com.chatar.gedcom.exception.ExceptionThrow;
import com.chatar.gedcom.tree.EntityTree;
import com.chatar.gedcom.tree.Node;
import com.chatar.gedcom.util.Assumptions;

public class XmlBuilder implements FileBuilder {

	private DocumentBuilderFactory docFactory;
	private Document document;
	private File file;

	public XmlBuilder(String path) {
		this.file = new File(path);
		docFactory = DocumentBuilderFactory.newInstance();
	}

	public String buildFrom(EntityTree entityTree) {
		ExceptionThrow.runTimeException("EntityTree name can't be null", entityTree == null);

		try {
			document = docFactory.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
		Element rootElement = document.createElement(entityTree.root());
		addTreeToXml(rootElement, entityTree.getChildNodes());
		document.appendChild(rootElement);
		writeDocumentToFile(document, file);
		return file.getAbsolutePath();
	}

	private void addTreeToXml(Element element, List<Node> childNodes) {
		for (Node childNode : childNodes) {
			Element childElement = document.createElement(childNode.getName());
			if (!childNode.getChildNodes().isEmpty()) {
				if (childNode.getValue() != null && childNode.getValue().startsWith(Assumptions.ID_STARTING)) {
					childElement.setAttribute(Assumptions.ID, childNode.getValue());
				} else if (childNode.getValue() != null) {
					childElement.setTextContent(childNode.getValue());
				}
				addTreeToXml(childElement, childNode.getChildNodes());
			} else if (childNode.getValue() != null && childNode.getValue().startsWith(Assumptions.ID_STARTING)) {
				childElement.setAttribute(Assumptions.ID, childNode.getValue());
			} else {
				childElement.setTextContent(childNode.getValue());
			}
			element.appendChild(childElement);
		}
	}

	public void writeDocumentToFile(Document document, File outputFile) {
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
