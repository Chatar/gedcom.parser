package com.chatar.gedcom.file;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RecordTest {

	@Test
	public void testMultpleSpacesDoesNotMatter() {
		Record record = new Record("   1     NAME     VALUE   ");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("NAME"));
		assertThat(record.getValue(), equalTo("VALUE"));
	}
	
	
	@Test
	public void testRecord1() {
		Record record = new Record("0 @I0001@ INDI");
		assertThat(record.getLevel(), equalTo(0));
		assertThat(record.getName(), equalTo("@I0001@"));
		assertThat(record.getValue(), equalTo("INDI"));
	}
	
	@Test
	public void testRecord2() {
		Record record = new Record("1 NAME Elizabeth Alexandra Mary /Windsor/");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("NAME"));
		assertThat(record.getValue(), equalTo("Elizabeth Alexandra Mary /Windsor/"));
	}
	
	@Test
	public void testRecord3() {
		Record record = new Record("1 SEX F");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("SEX"));
		assertThat(record.getValue(), equalTo("F"));
	}
	
	@Test
	public void testRecord4() {
		Record record = new Record("1 BIRT");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("BIRT"));
		assertThat(record.getValue(), equalTo(null));
	}
	
	@Test
	public void testRecord5() {
		Record record = new Record("2 DATE 21 Apr 1926");
		assertThat(record.getLevel(), equalTo(2));
		assertThat(record.getName(), equalTo("DATE"));
		assertThat(record.getValue(), equalTo("21 Apr 1926"));
	}
	
	@Test
	public void testRecord6() {
		Record record = new Record("2 PLAC 17 Bruton Street, London, W1");
		assertThat(record.getLevel(), equalTo(2));
		assertThat(record.getName(), equalTo("PLAC"));
		assertThat(record.getValue(), equalTo("17 Bruton Street, London, W1"));
	}
	
	@Test
	public void testRecord7() {
		Record record = new Record("1 OCCU Queen");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("OCCU"));
		assertThat(record.getValue(), equalTo("Queen"));
	}
	
	@Test
	public void testRecord8() {
		Record record = new Record("1 FAMC @F0003@");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("FAMC"));
		assertThat(record.getValue(), equalTo("@F0003@"));
	}
	
	@Test
	public void testRecord9() {
		Record record = new Record("1 FAMS @F0001@");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("FAMS"));
		assertThat(record.getValue(), equalTo("@F0001@"));
	}
	
	@Test
	public void testRecord10() {
		Record record = new Record("1 NOTE @N0002@");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("NOTE"));
		assertThat(record.getValue(), equalTo("@N0002@"));
	}
	
	@Test
	public void testRecord11() {
		Record record = new Record("1 CHAN");
		assertThat(record.getLevel(), equalTo(1));
		assertThat(record.getName(), equalTo("CHAN"));
		assertThat(record.getValue(), equalTo(null));
	}
	
	@Test
	public void testRecord12() {
		Record record = new Record("2 DATE 13 Dec 2003");
		assertThat(record.getLevel(), equalTo(2));
		assertThat(record.getName(), equalTo("DATE"));
		assertThat(record.getValue(), equalTo("13 Dec 2003"));
	}
}
