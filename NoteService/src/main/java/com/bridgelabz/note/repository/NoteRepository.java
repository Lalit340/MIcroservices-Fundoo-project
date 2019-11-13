package com.bridgelabz.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.note.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	Optional<Note> findByNoteIdAndUserId(long noteId, long userId);

	List<Note> findNoteByUserId(long userId);

}
