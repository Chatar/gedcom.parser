package com.chatar.gedcom.file;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Entity {

	private List<String> lines = new LinkedList<String>();
			
	public Entity addLine(String line) {
		if(StringUtils.isNotEmpty(line)) {
			lines.add(line);
		}
		return this;
	}
	
	public int size() {
		return lines.size();
	}
}
