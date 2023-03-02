package com.example.demo.Entity;



import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	private int post_id;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
}
