package com.example.demo.Payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.jdbc.core.RowCallbackHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	
	private int user_id;
	@NotEmpty
	@Size(min=4,message="user must be of 4 characters")
	private String name;
	@Email(message="Email Adress is not valid")
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;
	
	
	
}
