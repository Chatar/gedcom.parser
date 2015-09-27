package com.chatar.gedcom.tree;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NodeTest {

	@Test
	public void shouldAbleToAddParentNode() {
		Node node = new Node(null, 0, "INDI", "@I0001@", childNode());
		
		assertThat(node.isleaf(), is(false));
		assertThat(node.getName(), equalTo("INDI"));
		assertThat(node.getValue(), equalTo("@I0001@"));
		assertThat(node.getChildNodes().size(), equalTo(3));
	}
	
	@Test
	public void shouldAbleToAddChildToParent() {
		Node node = new Node(null, 0, "INDI", "@I0001@", childNode());
		node.addChildNode(new Node(node, 1,"another", "node"));
		
		assertThat(node.getChildNodes().size(), equalTo(4));
	}

	@Test
	public void shouldAbleToAddLeafNode() {
		Node node = new Node(null, 3, "dob", "19801111");
		
		assertThat(node.isleaf(), is(true));
	}
	
	private List<Node> childNode() {
		List<Node> children = new ArrayList<Node>();
		Node name = new Node(null, 0, "NAME", "Elizabeth Alexandra Mary /Windsor/");
		Node sex = new Node(null, 0, "SEX", "F");
		Node birt = new Node(null, 0, "BIRT", null);
		children.add(name);
		children.add(sex);
		children.add(birt);
		return children;
	}
}
