package com.chatar.gedcom.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.chatar.gedcom.exception.ExceptionThrow;
import com.chatar.gedcom.util.Assumptions;

public class GedcomFileParser implements FileParser {

	private List<Entity> entities;
	
	public GedcomFileParser() {
		entities = new ArrayList<Entity>();
	}

	public List<Entity> parse(String fileName) throws IOException {
		ExceptionThrow.runTimeException("File name can't be null", fileName == null);

		Entity entity = null;
		for(String record : getRecordsFromFile(fileName)) {
			if (isStartOfFirstEntity(record)) {
				if(entity != null) {
					entities.add(entity);
				}
				entity = new Entity();
			} 
			entity.addRecord(record);
		}
		return entities;
	}

	private boolean isStartOfFirstEntity(String record) {
		return record != null && record.startsWith(String.valueOf(Assumptions.FIRST_LEVEL));
	}

	private List<String> getRecordsFromFile(String fileName)
			throws IOException {
		return Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
	}
}
