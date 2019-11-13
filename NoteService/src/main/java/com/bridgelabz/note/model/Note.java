package com.bridgelabz.note.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "note")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Note implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "noteId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long noteId;

	@Column(name = "userId")
	private long userId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "isPin")
	private boolean isPin;

	@Column(name = "isArchive")
	private boolean isArchive;

	@Column(name = "isTrash")
	private boolean isTrash;

	@Column(name = "created")
	private LocalDate created;

	@Column(name = "modified")
	private LocalDate modified;

	@Column(name = "reminder")
	private String reminder;

	@Column(name = "color")
	private String color;

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	List<Label> label;
}
