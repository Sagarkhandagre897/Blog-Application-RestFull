package com.example.demo.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.ApiResponse;
import com.example.demo.Payloads.CommentDto;
import com.example.demo.dao.CommentDao;

@RestController
@RequestMapping("/api/comments")
@Validated
public class CommentController {
	
	@Autowired
	private CommentDao commentDao;

	@PostMapping("/postId/{post_id}/userId/{user_id}")
	public ResponseEntity<List<CommentDto>> create( @Valid @RequestBody CommentDto commentDto,@PathVariable int user_id,@PathVariable int post_id){
		
		List<CommentDto> res=this.commentDao.createComment(commentDto, post_id, user_id);
		
		return new ResponseEntity<List<CommentDto>>(res,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{comment_id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable int  comment_id){
		
		this.commentDao.deleteComment(comment_id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true),HttpStatus.OK);
	}
	
	@PutMapping("update/commentId/{comment_id}")
	public ResponseEntity<CommentDto> update(@Valid @RequestBody CommentDto commentDto,@PathVariable  int comment_id){
		
		CommentDto updated=this.commentDao.updateComment(commentDto, comment_id);
		
		return new ResponseEntity<CommentDto>(updated,HttpStatus.OK);
	}
	
	@GetMapping("/{comment_id}")
	public ResponseEntity<CommentDto> getById(@PathVariable int comment_id){
		
		CommentDto get= this.commentDao.getCommentById(comment_id);
		
		return new ResponseEntity<CommentDto>(get,HttpStatus.OK);
	}
	@GetMapping("/")
     public ResponseEntity<List<CommentDto>> getById(){
		
		List<CommentDto> list= this.commentDao.getAllComments();
		
		return new ResponseEntity<List<CommentDto>>(list,HttpStatus.OK);
	}
	
}
