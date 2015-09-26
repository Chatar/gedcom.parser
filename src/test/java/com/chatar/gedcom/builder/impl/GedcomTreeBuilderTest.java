package com.chatar.gedcom.builder.impl;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.chatar.gedcom.file.Entity;
import com.chatar.gedcom.tree.EntityTree;
import com.chatar.gedcom.tree.MockEntities;
import com.chatar.gedcom.tree.Node;
import com.chatar.gedcom.util.Assumptions;

public class GedcomTreeBuilderTest {

	private EntityTree entityTree;
	
	@Before
	public void setUp() {
		GedcomTreeBuilder gedcomTreeBuilder = new GedcomTreeBuilder();
		entityTree = gedcomTreeBuilder.build(entities());
	}
	
	@Test
	public void shouldAbleToBuildTree() {
		assertThat(entityTree.root(), equalTo("gedcom"));
		assertThat(_10001().getChildNodes().size(), equalTo(8));
		assertThat(_10001().getChildNodes().get(2).getChildNodes().size(), equalTo(2));
		assertThat(_10001().getChildNodes().get(7).getChildNodes().size(), equalTo(1));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForLevel0() {
		assertThat(_10001().getLevel(), equalTo(Assumptions.FIRST_LEVEL));
		assertThat(_10001().getName(), equalTo("INDI"));
		assertThat(_10001().getValue(), equalTo("@I0001@"));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForFirstChildInLevel1() {
		assertThat(_10001().getChildNodes().get(0).getLevel(), equalTo(1));
		assertThat(_10001().getChildNodes().get(0).getName(), equalTo("NAME"));
		assertThat(_10001().getChildNodes().get(0).getValue(), equalTo("Elizabeth Alexandra Mary /Windsor/"));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForSecondChildInLevel1() {
		assertThat(_10001().getChildNodes().get(1).getLevel(), equalTo(1));
		assertThat(_10001().getChildNodes().get(1).getName(), equalTo("SEX"));
		assertThat(_10001().getChildNodes().get(1).getValue(), equalTo("F"));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForThirdChildInLevel1() {
		assertThat(_10001().getChildNodes().get(2).getLevel(), equalTo(1));
		assertThat(_10001().getChildNodes().get(2).getName(), equalTo("BIRT"));
		assertThat(_10001().getChildNodes().get(2).getValue(), equalTo(null));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForFirstChildInLevel2() {
		assertThat(_10001().getChildNodes().get(2).getChildNodes().get(0).getLevel(), equalTo(2));
		assertThat(_10001().getChildNodes().get(2).getChildNodes().get(0).getName(), equalTo("DATE"));
		assertThat(_10001().getChildNodes().get(2).getChildNodes().get(0).getValue(), equalTo("21 Apr 1926"));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForSecondChildInLevel2() {
		assertThat(_10001().getChildNodes().get(2).getChildNodes().get(1).getLevel(), equalTo(2));
		assertThat(_10001().getChildNodes().get(2).getChildNodes().get(1).getName(), equalTo("PLAC"));
		assertThat(_10001().getChildNodes().get(2).getChildNodes().get(1).getValue(), equalTo("17 Bruton Street, London, W1"));
	}

	@Test
	public void nodeValuesAreAsExpectedForFourthChildInLevel1() {
		assertThat(_10001().getChildNodes().get(3).getLevel(), equalTo(1));
		assertThat(_10001().getChildNodes().get(3).getName(), equalTo("OCCU"));
		assertThat(_10001().getChildNodes().get(3).getValue(), equalTo("Queen"));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForFifthChildInLevel1() {
		assertThat(_10001().getChildNodes().get(4).getLevel(), equalTo(1));
		assertThat(_10001().getChildNodes().get(4).getName(), equalTo("FAMC"));
		assertThat(_10001().getChildNodes().get(4).getValue(), equalTo("@F0003@"));
	}

	@Test
	public void nodeValuesAreAsExpectedForSixthChildInLevel1() {
		assertThat(_10001().getChildNodes().get(5).getLevel(), equalTo(1));
		assertThat(_10001().getChildNodes().get(5).getName(), equalTo("FAMS"));
		assertThat(_10001().getChildNodes().get(5).getValue(), equalTo("@F0001@"));
	}

	@Test
	public void nodeValuesAreAsExpectedForSeventhChildInLevel1() {
		assertThat(_10001().getChildNodes().get(6).getLevel(), equalTo(1));
		assertThat(_10001().getChildNodes().get(6).getName(), equalTo("NOTE"));
		assertThat(_10001().getChildNodes().get(6).getValue(), equalTo("@N0002@"));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForEightChildInLevel1() {
		assertThat(_10001().getChildNodes().get(7).getLevel(), equalTo(1));
		assertThat(_10001().getChildNodes().get(7).getName(), equalTo("CHAN"));
		assertThat(_10001().getChildNodes().get(7).getValue(), equalTo(null));
	}
	
	@Test
	public void nodeValuesAreAsExpectedForEightChildInLevel2() {
		assertThat(_10001().getChildNodes().get(7).getChildNodes().get(0).getLevel(), equalTo(2));
		assertThat(_10001().getChildNodes().get(7).getChildNodes().get(0).getName(), equalTo("DATE"));
		assertThat(_10001().getChildNodes().get(7).getChildNodes().get(0).getValue(), equalTo("13 Dec 2003"));
	}

	private Node _10001() {
		return entityTree.getChildNodes().get(0);
	}

	private List<Entity> entities() {
		return Arrays.asList(MockEntities.firstEntity());
	}
}

//                                                                             gedcom
//																				 |
//																				 |
//																				 |
//0 @I0001@ INDI                                                              @10001@   
//1 NAME Elizabeth Alexandra Mary /Windsor/          					  /	     |      \
//1 SEX F																 /       |       \
//1 BIRT															    /		 |		  \										       
//2 DATE 21 Apr 1926												   /         |         \
//2 PLAC 17 Bruton Street, London, W1			NAME------SEX-------BIRT--------OCCU-------FAMC-------FAMS-------NOTE-------CHAN
//1 OCCU Queen														/\													      |	
//1 FAMC @F0003@                                                   /  \			           									  |
//1 FAMS @F0001@                                                  /    \													  |
//1 NOTE @N0002@                                                 /      \													  |
//1 CHAN													   DATE	   PLACE											     DATE
//2 DATE 13 Dec 2003
