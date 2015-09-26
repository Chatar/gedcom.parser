package com.chatar.gedcom.builder.impl;

import java.util.ArrayList;
import java.util.List;

import com.chatar.gedcom.builder.TreeBuilder;
import com.chatar.gedcom.exception.ExceptionThrow;
import com.chatar.gedcom.file.Entity;
import com.chatar.gedcom.file.Record;
import com.chatar.gedcom.tree.EntityTree;
import com.chatar.gedcom.tree.Node;
import com.chatar.gedcom.util.Assumptions;

public class GedcomTreeBuilder implements TreeBuilder {

	public EntityTree build(List<Entity> entities) {
		ExceptionThrow.runTimeException("Entities can't be null", entities == null);
		
		return new EntityTree(Assumptions.ROOT, buildNodesFrom(entities));
	}

	private List<Node> buildNodesFrom(List<Entity> entities) {
		List<Node> nodes = new ArrayList<Node>();
		for (Entity entity : entities) {
			nodes.add(buildNodeFrom(entity));
		}
		return nodes;
	}

	private Node buildNodeFrom(Entity entity) {
		Node parentNode = null, previousNode = null, currentNode = null, rootNode = null;
		int currentLevel = -1, previousLevel = -1;

		for (Record record : entity.getRecords()) {
			currentLevel = record.getLevel();
			String name = record.getName();
			String value = record.getValue();
			
			if (itsFirstLevel(currentLevel)) {
				rootNode = currentNode = new Node(parentNode, currentLevel, value, name);
			} else if (levelIsSame(currentLevel, previousLevel)) {
				currentNode = new Node(parentNode, currentLevel, name, value);
				parentNode.addChildNode(currentNode);
			} else if (itsChildNode(currentLevel, previousLevel)) {
				parentNode = previousNode;
				currentNode = new Node(parentNode, currentLevel, name, value);
				parentNode.addChildNode(currentNode);
			} else if (itsParentNode(currentLevel, previousLevel)) {
				parentNode = parentNode.parent();
				currentNode = new Node(parentNode, currentLevel, name, value);
				parentNode.addChildNode(currentNode);
			}
			previousLevel = currentLevel;
			previousNode = currentNode;
		}
		return rootNode;
	}

	private boolean itsParentNode(int currentLevel, int previousLevel) {
		return currentLevel < previousLevel;
	}

	private boolean itsChildNode(int currentLevel, int previousLevel) {
		return currentLevel > previousLevel;
	}

	private boolean levelIsSame(int currentLevel, int previousLevel) {
		return currentLevel == previousLevel;
	}

	private boolean itsFirstLevel(int currentLevel) {
		return Assumptions.FIRST_LEVEL == currentLevel;
	}
}
