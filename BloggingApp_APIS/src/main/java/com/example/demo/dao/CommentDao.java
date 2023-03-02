package com.example.demo.dao;

import java.util.List;

import com.example.demo.Payloads.CommentDto;

public interface CommentDao {

	List<CommentDto> createComment(CommentDto commentDto,int post_id,int user_id);
	
	void deleteComment(int comment_id);
	
	CommentDto getCommentById(int comment_id);
	
	CommentDto updateComment(CommentDto commentDto,int comment_id);
	
	List<CommentDto> getAllComments();
	
	List<CommentDto> getCommentByUser(int user_id);
	
	List<CommentDto> getCommentByPost(int post_id);
	
}
