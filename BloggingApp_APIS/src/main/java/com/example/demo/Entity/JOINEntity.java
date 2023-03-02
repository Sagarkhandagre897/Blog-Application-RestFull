package com.example.demo.Entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JOINEntity {

  private int post_id;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
	
    private int user_id;

    private String name;

    private String email;

    private String password;

    private String about;
    

	private int category_id;
	
	private String categoryTitle;
	
	private String categoryDescription;
	
	private int comment_id;
	
	private String contentOfComment;
	
}
