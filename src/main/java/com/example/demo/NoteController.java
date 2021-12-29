package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
	@Autowired
    private NoteService noteService = new NoteServiceImpl();

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getOrderPage(Model model) {
        List<NoteItem> orders = noteService.getAll();
        model.addAttribute("noteList", orders);
        return "Notes keeper";
    }
    @GetMapping("/list")
    List<NoteItem> getAllNotes() {
        return noteService.getAll();
    }
    @GetMapping("/get/{id}")
    NoteItem getNoteById(@PathVariable int id) {
    	if (id > 0) {
    		return noteService.getById(id);
        } else {
            return null;
        }
        
    }
    @GetMapping("/remove/{id}")
    void deleteNoteById(@PathVariable int id) {
    	noteService.delete(noteService.getById(id));
    }
    @PostMapping("/addOrEdit")
    ResponseEntity<Void> createOrUpdateNote(@RequestBody NoteItem note) {
    	noteService.addOrEdit(note);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/search/{textPart}")
    List<NoteItem> searchNote(@PathVariable String textPart) {
    	return noteService.search(textPart);
    }
    @GetMapping("/filter")//after/{afterTime}/before/{beforeTime}/hashtag/{hashTag}")
    List<NoteItem> getNoteByTime(@RequestParam(required = false) Long afterTime, @RequestParam(required = false) Long beforeTime, @RequestParam(required = false) String hashTag) {
    	if(afterTime == null) afterTime = 0L;
    	if(beforeTime == null) beforeTime = 0L;
    	return noteService.filter(afterTime, beforeTime, hashTag);
    }
}
