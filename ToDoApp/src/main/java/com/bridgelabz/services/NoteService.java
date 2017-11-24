package com.bridgelabz.services;

import java.util.List;

import com.bridgelabz.model.Note;

public interface NoteService {
	
	boolean addNote(Note note);
	
	boolean deleteNote(int noteId);
	
	boolean updateNote(Note note);
	
	List<Note> getNotes();
	
	Note getNoteById(int id);
}
