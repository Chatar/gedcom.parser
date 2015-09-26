package com.chatar.gedcom.file;

import java.io.IOException;
import java.util.List;

public interface FileParser {

	public List<Entity> parse(String fileName) throws IOException;
}
