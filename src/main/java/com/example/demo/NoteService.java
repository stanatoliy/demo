package com.example.demo;

import java.util.List;

public interface NoteService {
    void addOrEdit(NoteItem note);
    void delete(NoteItem note);
    List<NoteItem> getAll();
    NoteItem getById(Integer id);
	List<NoteItem> search(String textPart);
	List<NoteItem> filter(long after, long before, String hashTag);
}
