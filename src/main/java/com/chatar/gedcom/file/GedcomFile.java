package com.chatar.gedcom.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.chatar.gedcom.exception.ExceptionThrow;

public class GedcomFile {

	private List<Entity> records;
	private String entityRoot = "0";
	
	public GedcomFile() {
		records = new ArrayList<Entity>();
	}

	public void loadFrom(String fileName) throws IOException {
		ExceptionThrow.runTimeException("File name can't be null", fileName == null);
		List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
		
		Entity record = null;
		for(String line : lines) {
			if (line != null && line.startsWith(entityRoot)) {
				if(record != null) {
					records.add(record);
				}
				record = new Entity();
			} else {
				record.addLine(line);
			}
		}
	}
	
	public int records() {
		return records.size();
	}
}
