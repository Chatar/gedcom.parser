package com.chatar.gedcom.file;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Entity {

	private List<Record> records = new LinkedList<Record>();
			
	public Entity addRecord(String record) {
		if(StringUtils.isNotEmpty(record)) {
			records.add(new Record(record));
		}
		return this;
	}
	
	public int size() {
		return records.size();
	}
	
	public List<Record> getRecords() {
		return records;
	}

	@Override
	public String toString() {
		return "Entity [records=" + records + "]";
	}
}
