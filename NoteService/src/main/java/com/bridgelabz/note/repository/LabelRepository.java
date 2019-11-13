package com.bridgelabz.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.note.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label , Long>{
	
	Optional<Label> findByUserIdAndLabelId(long userId,long labelId);
	List<Label> findLabelByUserId(long userId);
}
