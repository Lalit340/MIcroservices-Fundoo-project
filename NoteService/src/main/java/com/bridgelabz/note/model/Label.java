package com.bridgelabz.note.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "label")
@Getter
@Setter
@NoArgsConstructor
public class Label {

	@Id
	@Column(name = "labelId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long labelId;

	@Column(name = "labelName")
	@NotNull(message = "Label name can not be null")
	@NotEmpty(message = "Label name can not be empty")
	private String labelName;

	@Column(name = "createdDate")
	private LocalDate createdDate;

	@Column(name = "modifiedDate")
	private LocalDate modifiedDate;

	@Column(name = "userId")
	private long userId;

	@ManyToMany(cascade = CascadeType.ALL)
	List<Note> notes;
}
