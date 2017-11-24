package com.bridgelabz.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.dao.NoteDao;
import com.bridgelabz.model.Note;

public class NoteServiceImpl implements NoteService{

	@Autowired
	NoteDao noteDao;
	
	public boolean addNote(Note note) {
		if(note!=null)
		{
			Date date = new Date();
			note.setCreatedDate(date);
			noteDao.addNote(note);
			return true;
		}
		return false;
	}

	public boolean deleteNote(int noteId) {
		noteDao.deleteNote(noteId);
		return true;
	}

	public boolean updateNote(Note note) {
		if(note!=null)
		{
			noteDao.updateNote(note);
			return true;
		}
		return false;
	}

	public List<Note> getNotes() {
		return noteDao.getNotes();
	}

	public Note getNoteById(int id) {
		return noteDao.getNoteById(id);
	}

}
