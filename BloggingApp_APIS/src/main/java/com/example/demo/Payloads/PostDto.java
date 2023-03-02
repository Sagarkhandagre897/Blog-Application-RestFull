package com.example.demo.Payloads;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import com.example.demo.Entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private int post_id;
	
    @NotEmpty
	private String title;
	@NotEmpty
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
}
