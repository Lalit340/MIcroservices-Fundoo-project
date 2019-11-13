package com.bridgelabz.note.service;

import java.util.List;

import com.bridgelabz.note.dto.CreateDto;
import com.bridgelabz.note.dto.UpdateDto;
import com.bridgelabz.note.model.Note;
import com.bridgelabz.response.Response;

public interface INoteService {

	/**
	 * Purpose : Function for creating notes
	 * @param createDto
	 * @param userId
	 * @return
	 */
	public Response createNote(CreateDto createDto, long userId);

	/**
	 * Purpose : Function for updating notes
	 * @param updateDto
	 * @param userId
	 * @param noteId
	 * @return
	 */
	public Response updateNote(UpdateDto updateDto, long userId, long noteId);

	/**
	 * Purpose : Function for deleting notes
	 * @param userId
	 * @param noteId
	 * @return
	 */
	public Response deleteNote(long userId, long noteId);

	public Note getSingleNoteFromRedis(long userId, long noteId);

	/**
	 *  Purpose : Function to get all notes
	 * @param userId
	 * @param isPin
	 * @param isTrash
	 * @param isArchive
	 * @return
	 */
	public List<Note> getAllNote(long userId, boolean isPin, boolean isTrash, boolean isArchive);
	
	
	/**
	 * Purpose : Function to pin or unpin notes 
	 * @param userId
	 * @param noteId
	 * @return
	 */
	public Response pinAndUnPin(long userId, long noteId);

	/**
	 * Purpose : Function to trash or untrash notes 
	 * @param userId
	 * @param noteId
	 * @return
	 */
	public Response trashAndUntrash(long userId, long noteId);

	/**
	 * Purpose : Function to archive or unarchive notes 
	 * @param userId
	 * @param noteId
	 * @return
	 */
	public Response archiveAndUnarchive(long userId, long noteId);

	/**
	 * Purpose : Function to add reminder
	 * @param userId
	 * @param noteId
	 * @param time
	 * @return
	 */
	public Response addReminder(long userId, long noteId, String time);

	/**
	 * Purpose : Function to remove reminder
	 * @param userId
	 * @param noteId
	 * @return
	 */
	public Response removeReminder(long userId, long noteId);

	

}