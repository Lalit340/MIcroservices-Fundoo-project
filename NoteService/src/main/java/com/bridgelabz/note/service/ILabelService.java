package com.bridgelabz.note.service;

import java.util.List;

import com.bridgelabz.note.dto.LabelDto;
import com.bridgelabz.note.model.Label;
import com.bridgelabz.response.Response;

public interface ILabelService {
	
	public Response createLabel(long userId,LabelDto labelDto);
	
	public Response updateLabel(long userId , long labelId , LabelDto labelDto);
	
	public List<Label> getLabel(long userId);
	
	public Response deleteLabel(long userId , long labelId);
	
	public Response addLabelToNote(long userId , long labelId , long noteId);
	
	public Response removeLabelFromNote(long userId , long labelId , long noteId);
	
	public List<Label> getLabelOfNote(long userId, long noteId);
}
