package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NoteServiceImpl implements NoteService{
	@Autowired
	private NoteRepository noteRepository = new NoteRepositoryImpl();

    public void addOrEdit(NoteItem note) {
        if(note != null) {
        	NoteItem existNote = noteRepository.getById(note.getId());
        	if(existNote != null) {
        		existNote.setTitle(note.getTitle());
        		existNote.setText(note.getText());
        		existNote.setHashTags(note.getHashTags());
        	}else {
	            List<NoteItem> notesList = noteRepository.getAll();
	            int newId = 1;
	            if(!notesList.isEmpty()) {
	                NoteItem lastNote = notesList.get(notesList.size() - 1);
	                newId = lastNote.getId()+1;
	            }
	            note.setId(newId);
	            noteRepository.save(note);
        	}
        }
    }

    public void delete(NoteItem note) {
        if(note != null) {
            noteRepository.delete(note);
        }  
    }

    public List<NoteItem> getAll() {
        return noteRepository.getAll();
    }

    public NoteItem getById(Integer id) {
        if(id != null) {
            return noteRepository.getById(id);
        }
        return null;
    }

	@Override
	public List<NoteItem> search(String textPart) {
		return noteRepository.search(textPart);
	}

	@Override
	public List<NoteItem> filter(long after, long before, String hashTag) {
		return noteRepository.filter(after, before, hashTag);
	}

}
