package com.chatar.gedcom.tree;

import java.util.ArrayList;
import java.util.List;

public class EntityTree {

	private String root;
	private List<Node> childNodes;
	
	public EntityTree(String root) {
		this.root = root;
		this.childNodes = new ArrayList<Node>();
	}
	
	public EntityTree(String root, List<Node> childNodes) {
		this.root = root;
		this.childNodes = childNodes;
	}
	
	public String root() {
		return root;
	}
	
	public List<Node> getChildNodes() {
		return childNodes;
	}
}
