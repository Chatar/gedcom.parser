package com.chatar.gedcom.tree;

import java.util.ArrayList;
import java.util.List;

import com.chatar.gedcom.file.Entity;

public class MockEntities {

	public static List<Entity> build() {
		List<Entity> entities = new ArrayList<Entity>();
		
		entities.add(firstEntity());
		entities.add(secondEntity());
		return entities;
	}
	
	public static Entity firstEntity() {
		return new Entity().addRecord("0 @I0001@ INDI")
				.addRecord("1 NAME Elizabeth Alexandra Mary /Windsor/")
				.addRecord("1 SEX F")
				.addRecord("1 BIRT")
					.addRecord("2 DATE 21 Apr 1926")
					.addRecord("2 PLAC 17 Bruton Street, London, W1")
				.addRecord("1 OCCU Queen")
				.addRecord("1 FAMC @F0003@")
				.addRecord("1 FAMS @F0001@")
				.addRecord("1 NOTE @N0002@")
				.addRecord("1 CHAN")
					.addRecord("2 DATE 13 Dec 2003");
	}
	
	public static Entity secondEntity() {
		return new Entity().addRecord("0 @I0002@ INDI")
				.addRecord("1 NAME Philip /Mountbatten/")
				.addRecord("1 SEX M")
				.addRecord("1 BIRT")
					.addRecord("2 DATE 1921")
					.addRecord("1 TITL Duke of Edinburgh")
				.addRecord("1 FAMC @F0002@")
				.addRecord("1 FAMS @F0001@")
				.addRecord("1 NOTE @N0001@")
				.addRecord("1 CHAN")
					.addRecord("2 DATE  6 Mar 2004");
	}
}
