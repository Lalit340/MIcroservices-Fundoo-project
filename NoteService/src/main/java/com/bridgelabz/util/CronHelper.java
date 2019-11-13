package com.bridgelabz.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bridgelabz.note.model.Note;
import com.bridgelabz.note.repository.NoteRepository;

@Component
public class CronHelper {

	@Autowired
	private NoteRepository noteRepository;

	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void cronRunner() {
		System.out.println("I am schedular");
		List<Note> notes = noteRepository.findAll();
		for (Note note : notes) {
			if (note.isTrash() == true) {
				LocalDate currentDate = LocalDate.now();
				LocalDate lastModified = note.getModified();
				Period period = Period.between(currentDate, lastModified);
				long differenceDays = Math.abs(period.getDays());
				System.out.println("Diff in days : " + differenceDays);
				if (differenceDays >= 7) {
					noteRepository.delete(note);
					System.out.println("Note removed from trash permanently");
				}
			}
		}

	}

}
