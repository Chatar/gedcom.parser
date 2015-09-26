package com.chatar.gedcom.tree;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.chatar.gedcom.util.Assumptions;

public class EntityTreeTest {

	@Test
	public void test() {
		EntityTree entityTree = new EntityTree(Assumptions.ROOT, children());
		
		assertThat(entityTree.root(), equalTo(Assumptions.ROOT));
		assertThat(entityTree.getChildNodes().size(), equalTo(2));
	}

	private List<Node> children() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node(0, "name","person"));
		nodes.add(new Node(0, "department","enginnering", grandChildren()));
		return nodes;
	}

	private List<Node> grandChildren() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node(1, "department_floor","7th"));
		nodes.add(new Node(1, "department_head","Mr. Paul"));

		return nodes;	
	}
}



