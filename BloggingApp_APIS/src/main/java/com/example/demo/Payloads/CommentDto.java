package com.example.demo.Payloads;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private int comment_id;
	@NotEmpty
	private String contentOfComment;
}
