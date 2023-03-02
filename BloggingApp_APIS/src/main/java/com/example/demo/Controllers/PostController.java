package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.ApiResponse;
import com.example.demo.Entity.JOINEntity;
import com.example.demo.Entity.Post;
import com.example.demo.Entity.Role;
import com.example.demo.Payloads.PostDto;
import com.example.demo.dao.PostDao;
import com.example.demo.dao.RoleDao;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostDao postDao;
	@Autowired
	private RoleDao roleDao;
	
	@PostMapping("/userId/{user_id}/categoryId/{category_id}")
	public ResponseEntity<List<JOINEntity>> create(@RequestBody PostDto postDto,@PathVariable int user_id, @PathVariable int category_id){
		
		List<JOINEntity> list=this.postDao.createPost(postDto, category_id, user_id);
		
		return new ResponseEntity<List<JOINEntity>>(list,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{post_id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable int post_id){
		
		int user_id = this.postDao.getUserByPostId(post_id);
		
		Role role=this.roleDao.roleByUserId(user_id);
		
		if(role.getRole_name().equals("ADMIN")) {
			
			this.postDao.deletePost(post_id);
			return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully",true),HttpStatus.OK);
		}
		
				
		return new ResponseEntity<ApiResponse>(new ApiResponse("this user does not have permission to delete post",true),HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<JOINEntity>> getAllPost(){
		
		List<JOINEntity> allPost=this.postDao.getAllPost();
		
		return new ResponseEntity<List<JOINEntity>>(allPost,HttpStatus.OK);
	}
	
	@GetMapping("/{post_id}")
	public ResponseEntity<JOINEntity> getPostById(@PathVariable int post_id){
		
		JOINEntity post= this.postDao.getPostById(post_id);
		
		return new ResponseEntity<JOINEntity>(post,HttpStatus.OK);
	}
	
	@PutMapping("/{post_id}")
	public ResponseEntity<JOINEntity> update(@RequestBody PostDto postDto,@PathVariable int post_id){
		
		JOINEntity updated =this.postDao.updatePost(postDto, post_id);
		
		return new ResponseEntity<JOINEntity>(updated,HttpStatus.OK);
		
	}
	@GetMapping("by/userId/{user_id}")
	public ResponseEntity<List<Post>> getPostByUser(@PathVariable int user_id ){
		
		List<Post> post=this.postDao.getPostByUser(user_id);
		
		return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
	}
	@GetMapping("by/categoryId/{category_id}")
	public ResponseEntity<List<Post>> getPostByCategory(@PathVariable int category_id ){
		
		List<Post> post=this.postDao.getPostByCategory(category_id);
		
		return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
	}
}
