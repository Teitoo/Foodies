package com.sideproject.foodies.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserDTO {
	@Getter
	private String username;
	@Getter
	private String email;
//	@Getter
//	private String phoneNumber;
//	@Getter
//	private String address;
	@Getter
	private Role role;
}
