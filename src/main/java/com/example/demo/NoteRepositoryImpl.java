package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import storage.LocalFiles;

@Repository
public class NoteRepositoryImpl implements NoteRepository{
	private List<NoteItem> notes;// = new ArrayList<NoteItem>();
	public NoteRepositoryImpl() {
		notes = LocalFiles.serializeDataIn();
		/*
		NoteItem note = new NoteItem("justTitle", "Dont forget something important", null);
		note.setId(1);
		notes.add(note);
		*/
	}
	@Override
	public void save(NoteItem note) {
		notes.add(note);
		saveNotesList();
	}

	@Override
	public void delete(NoteItem note) {
		notes.remove(note);
		saveNotesList();
	}
	private void saveNotesList() {
		LocalFiles.serializeDataOut(notes);
	}
	@Override
	public List<NoteItem> getAll() {
		return notes;
	}

	@Override
	public NoteItem getById(int id) {
		if(id > 0) {
			for (NoteItem item : notes) {
		        if (item.getId() == id) {
		            return item;
		        }
		    }
		}
		return null;
	}
	@Override
	public void add(NoteItem note) {
		if(note != null) {
			notes.add(note);
			saveNotesList();
		}
	}
	@Override
	public List<NoteItem> search(String textPart) {
		List<NoteItem> res = new ArrayList<NoteItem>();
		if(!textPart.equals("") && textPart != null) {
			textPart = textPart.toLowerCase();
			for (NoteItem item : notes) {
		        if (item.getTitle().toLowerCase().indexOf(textPart) != -1
		        		|| item.getText().toLowerCase().indexOf(textPart) != -1) {
		            res.add(item);
		        }
		    }
		}
		return res;
	}
	@Override
	public List<NoteItem> filter(long after, long before, String hashTag) {
		List<NoteItem> res = new ArrayList<NoteItem>();
		for (NoteItem item : notes) {
	        if (item.getCreationTime() >= after
	        		&& (before == 0 || item.getCreationTime() <= before)
	        		&& (hashTag == null || hashTag.equals("") || item.hasHashTag(hashTag))) {
	            res.add(item);
	        }
	    }
		return res;
	}
}
