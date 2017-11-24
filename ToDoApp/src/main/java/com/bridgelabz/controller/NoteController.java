package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.Note;
import com.bridgelabz.model.Response;
import com.bridgelabz.services.NoteService;

@RestController
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	@RequestMapping(value = "/addNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addNote(@RequestBody Note note)
	{
		Response response=new Response();
		if(note!=null)
		{
			if(noteService.addNote(note))
			{
				response.setMessage("Note Successfully added.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
			response.setMessage("Note is not added.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
	@RequestMapping(value="/deleteNote/{id}", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteNote(@PathVariable int id)
	{
		Response response=new Response();
		if(noteService.deleteNote(id))
		{
			response.setMessage("Note successfully deleted.");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		}
		response.setMessage("Note is not deleted.");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

	}
}
