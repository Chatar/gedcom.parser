package com.chatar.gedcom.builder;

import java.util.List;

import com.chatar.gedcom.file.Entity;
import com.chatar.gedcom.tree.EntityTree;

public interface TreeBuilder {

	public EntityTree build(List<Entity> entities);
}
