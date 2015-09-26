package com.chatar.gedcom.file;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class EntityTest {

	@Test
	public void sizeShouldBeZeroIfNoLineAddedInEntity() {
		Entity entity = new Entity();
		assertThat(entity.size(), is(0));
	}
	
	@Test
	public void shouldAbleToAddLineInEntity() {
		Entity entity = new Entity();
		entity.addRecord("0 @I0001@ INDI").addRecord("1 NAME Elizabeth Alexandra Mary /Windsor/");
		assertThat(entity.size(), is(2));
	}
	
	@Test
	public void shouldIgnoreNullOrEmptyLine() {
		Entity entity = new Entity();
		entity.addRecord(null);
		entity.addRecord("");
		assertThat(entity.size(), is(0));
	}
}
