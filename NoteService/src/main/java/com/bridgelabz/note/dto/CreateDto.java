package com.bridgelabz.note.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDto {
	private String title;
	private String description;
	private String color;
}
