package com.bridgelabz.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.dao.NoteDao;
import com.bridgelabz.model.Note;

/**
 * @author Deepak Prajapati
 * @Description This class is a service provider which implements NoteService class.
 */
public class NoteServiceImpl implements NoteService{

	@Autowired
	NoteDao noteDao;
	
	/* (non-Javadoc)
	 * @see com.bridgelabz.services.NoteService#addNote(com.bridgelabz.model.Note)
	 */
	public boolean addNote(Note note) {
		if(note!=null)
		{
			Date date = new Date();
			note.setCreatedDate(date);
			note.setLastUpdate(date);
			
			boolean check= noteDao.addNote(note);
			System.out.println("check : "+check);
			return check;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.services.NoteService#deleteNote(int)
	 */
	public boolean deleteNote(int noteId) {
		boolean check= noteDao.deleteNote(noteId);
		System.out.println("check : "+check);
		return check;
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.services.NoteService#updateNote(com.bridgelabz.model.Note)
	 */
	public boolean updateNote(Note note) {
		if(note!=null)
		{
			Date date=new Date();
			note.setLastUpdate(date);
			return noteDao.updateNote(note);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.services.NoteService#getNotes()
	 */
	public List<Note> getNotes() {
		return noteDao.getNotes();
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.services.NoteService#getNoteById(int)
	 */
	public Note getNoteById(int id) {
		return noteDao.getNoteById(id);
	}

}
