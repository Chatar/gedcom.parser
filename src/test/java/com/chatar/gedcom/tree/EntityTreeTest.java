package com.chatar.gedcom.tree;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.chatar.gedcom.util.Assumptions;

public class EntityTreeTest {

	@Test
	public void testIfEntityTreeBuildCorrectly() {
		EntityTree entityTree = new EntityTree(Assumptions.ROOT, children());
		assertThat(entityTree.root(), equalTo(Assumptions.ROOT));
		assertThat(entityTree.getChildNodes().size(), equalTo(2));
	}

	private List<Node> children() {
		List<Node> nodes = new ArrayList<Node>();
		Node parent = null;
		nodes.add(new Node(parent, 0, "name","person"));
		Node anotherNode = new Node(parent, 0, "department","enginnering");
		anotherNode.setChildNodes(grandChildren(anotherNode));
		nodes.add(anotherNode);
		return nodes;
	}

	private List<Node> grandChildren(Node parent) {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node(parent, 1, "department_floor","7th"));
		nodes.add(new Node(parent, 1, "department_head","Mr. Paul"));
		return nodes;	
	}
}



