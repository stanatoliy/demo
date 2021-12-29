package com.example.demo;

import java.util.List;

public interface NoteRepository {
	void save(NoteItem note);
	void add(NoteItem note);
    void delete(NoteItem note);
    List<NoteItem> search(String textPart);
    List<NoteItem> getAll();
    NoteItem getById(int id);
	List<NoteItem> filter(long after, long before, String hashTag);
}
