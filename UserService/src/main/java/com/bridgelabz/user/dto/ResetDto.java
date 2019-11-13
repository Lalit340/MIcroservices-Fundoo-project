package com.bridgelabz.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResetDto {
		private String newPassword;
		private String conformPassword;

}
