package com.chatar.gedcom.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private String name;
	private String value;
	private int level;
	private List<Node> childNodes;
	private Node parent;
	
	public Node(int level, String name) {
		this(null, level, name, null, new ArrayList<Node>());
	}
	
	public Node(int level, String name, String value) {
		this(null, level, name, value, new ArrayList<Node>());
	}
	
	public Node(int level, String name, String value, List<Node> childNodes) {
		this(null, level, name, value, childNodes);
	}
	
	public Node(Node parent, int level, String name) {
		this(parent, level, name, null, new ArrayList<Node>());
	}
	
	public Node(Node parent, int level, String name, String value) {
		this(parent, level, name, value, new ArrayList<Node>());
	}
	
	public Node(Node parent, int level, String name, String value, List<Node> childNodes) {
		this.parent = parent;
		this.level = level;
		this.name = name;
		this.value = value;
		this.childNodes = childNodes;
	}

	public void addChildNode(Node child) {
		childNodes.add(child);
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public List<Node> getChildNodes() {
		return childNodes;
	}
	
	public boolean isleaf() {
		return childNodes.isEmpty();
	}
	
	public int getLevel() {
		return level;
	}
	
	public String toString() {
		String root =  "name : "+name +", value : "+ value +", level : "+level;
		
		for(Node node: childNodes) {
			root = root + "\n  "+ node.toString();
		}
		return root;
	}

	public Node parent() {
		return parent;
	}
}
