package com.bridgelabz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.Note;

public class NoteDao {
	
	@Autowired
	SessionFactory factory;
	
	/**
	 * @param note
	 * @return boolean
	 * @Description This method is used to add the notes into the database. 
	 * It returns true if data is inserted, otherwise it returns false.
	 */
	public boolean addNote(Note note)
	{
		if(note!=null){
			try{
				Session session= factory.openSession();
				Transaction transaction=session.beginTransaction();
				session.save(note);
				transaction.commit();
				session.close();
				return true;
			}catch(Exception e){
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param note
	 * @return boolean 
	 * @Description this method is used to delete the note, 
	 * it will return true if note is deleted, otherwise returns false.
	 */
	public boolean deleteNote(int noteId)
	{
		try {
			Session session=factory.openSession();
			Transaction transaction=session.beginTransaction();
			session.delete(noteId);
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param note
	 * @return boolean 
	 * @Description This method is used to update the note, 
	 * it returns true if note is updated otherwise returns false.
	 */
	public boolean updateNote(Note note)
	{
		try {
			Session session = factory.openSession();
			Transaction transaction=session.beginTransaction();
			session.update(note);
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @return List
	 * @Description this method is used to get the list of note from the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Note> getNotes()
	{
		Session session=factory.openSession();
		Query<Note> query = session.createQuery("from Note");
		List<Note> list = query.list();
		session.close();
		return list;
	}
	
	/**
	 * @param id
	 * @return Note
	 * 
	 * @Description It will return User if note available in database otherwise return null.
	 */
	@SuppressWarnings("rawtypes")
	public Note getNoteById(int id)
	{
		Session session = factory.openSession();
		Query query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);
		Note note = (Note) query.uniqueResult();
		session.close();
		return note;
	}
}