package com.chatar.gedcom.builder.impl;

import java.io.File;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.chatar.gedcom.builder.FileBuilder;
import com.chatar.gedcom.exception.ExceptionThrow;
import com.chatar.gedcom.tree.EntityTree;
import com.chatar.gedcom.tree.Node;
import com.chatar.gedcom.util.Assumptions;
import com.chatar.gedcom.util.XmlUtils;

public class XmlBuilder implements FileBuilder {

	private Document document;
	private File file;

	public XmlBuilder(String path) {
		this.file = new File(path);
	}

	public String buildFrom(EntityTree entityTree) {
		ExceptionThrow.runTimeException("EntityTree name can't be null", entityTree == null);

		document = XmlUtils.instanceOf();
		Element rootElement = document.createElement(entityTree.root());
		addTreeToXml(rootElement, entityTree.getChildNodes());
		document.appendChild(rootElement);
		XmlUtils.writeDocumentToFile(document, file);
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
}
