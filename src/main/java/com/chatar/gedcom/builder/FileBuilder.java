package com.chatar.gedcom.builder;

import com.chatar.gedcom.tree.EntityTree;

public interface FileBuilder {

	public String buildFrom(EntityTree recordTree);
}
