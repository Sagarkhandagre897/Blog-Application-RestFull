package com.example.demo.dao;

import java.util.List;

import com.example.demo.Entity.JOINEntity;
import com.example.demo.Entity.Post;
import com.example.demo.Payloads.PostDto;
import com.example.demo.Payloads.categoryDto;

public interface PostDao {

	 
	 JOINEntity updatePost(PostDto postDto,int post_id);
	 
	 JOINEntity getPostById(int post_id);
	 
	 void deletePost(int post_id);
	 
	 List<JOINEntity> getAllPost();
	 
	   List<Post> getPostByCategory(int category_id);
		
	   List<Post> getPostByUser(int user_id);

	List<JOINEntity> createPost(PostDto postDto, int category_id, int user_id);
	
	int getUserByPostId(int postId);

}
