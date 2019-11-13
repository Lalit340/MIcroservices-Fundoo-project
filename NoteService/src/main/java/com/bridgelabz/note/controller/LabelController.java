package com.bridgelabz.note.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.note.dto.LabelDto;
import com.bridgelabz.note.model.Label;
import com.bridgelabz.note.service.ILabelService;
import com.bridgelabz.response.Response;

@RestController
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
@RequestMapping("/label")
public class LabelController {

	@Autowired
	private ILabelService labelService;

	@PostMapping
	public ResponseEntity<Response> createLabel(HttpServletRequest request, @RequestHeader String token,
			@RequestBody LabelDto labelDto) {
		long userId = Long.parseLong(request.getAttribute("userId").toString());
		Response statusResponse = labelService.createLabel(userId, labelDto);
		return new ResponseEntity<Response>(statusResponse, HttpStatus.OK);
	}

	@PutMapping("/{labelId}")
	public ResponseEntity<Response> updateLabel(HttpServletRequest request, @RequestHeader String token,
			@PathVariable long labelId, @RequestBody LabelDto labelDto) {
		long userId = Long.parseLong(request.getAttribute("userId").toString());
		Response statusResponse = labelService.updateLabel(userId, labelId, labelDto);
		return new ResponseEntity<Response>(statusResponse, HttpStatus.OK);
	}

	@GetMapping
	public List<Label> getLabel(HttpServletRequest request, @RequestHeader String token) {
		long userId = Long.parseLong(request.getAttribute("userId").toString());
		List<Label> listLabels = labelService.getLabel(userId);
		return listLabels;
	}

	@DeleteMapping("/{labelId}")
	public ResponseEntity<Response> deleteLabel(HttpServletRequest request, @RequestHeader String token,
			@PathVariable long labelId) {
		long userId = Long.parseLong(request.getAttribute("userId").toString());
		Response statusResponse = labelService.deleteLabel(userId, labelId);
		return new ResponseEntity<Response>(statusResponse, HttpStatus.OK);
	}

	@PutMapping("/addlabeltonote")
	public ResponseEntity<Response> addLabelToNote(HttpServletRequest request, @RequestHeader String token,
			@RequestParam long noteId, @RequestParam long labelId) {
		long userId = Long.parseLong(request.getAttribute("userId").toString());
		Response statusResponse = labelService.addLabelToNote(userId, labelId, noteId);
		return new ResponseEntity<Response>(statusResponse, HttpStatus.OK);
	}

	@PutMapping("/removelabelfronnote")
	public ResponseEntity<Response> removeLabelFromNote(HttpServletRequest request, @RequestHeader String token,
			@RequestParam long noteId, @RequestParam long labelId) {
		long userId = Long.parseLong(request.getAttribute("userId").toString());
		Response statusResponse = labelService.removeLabelFromNote(userId, labelId, noteId);
		return new ResponseEntity<Response>(statusResponse, HttpStatus.OK);
	}

	@GetMapping("/getlabelofnote")
	public List<Label> getLabelOfNote(HttpServletRequest request, @RequestHeader String token,
			@RequestParam long noteId) {
		long userId = Long.parseLong(request.getAttribute("userId").toString());
		List<Label> listLabels = labelService.getLabelOfNote(userId, noteId);
		return listLabels;
	}
}
