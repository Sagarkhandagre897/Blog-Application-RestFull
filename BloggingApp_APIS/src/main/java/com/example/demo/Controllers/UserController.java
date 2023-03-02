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
import com.example.demo.Payloads.UserDto;

import com.example.demo.dao.UserDao;



@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

	
	@Autowired
	private UserDao dao;
	
	
	@PostMapping("/")
	public ResponseEntity<List<UserDto>> create(@Valid @RequestBody UserDto userDto){
		
		
		List<UserDto> createdUserDto = this.dao.create(userDto);
		
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> list= this.dao.getAllUsers();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		
		UserDto userDto=this.dao.getUserById(userId);
		
		return ResponseEntity.ok(userDto);
		
	}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> update(@Valid  @RequestBody UserDto userDto,@PathVariable Integer userId){
		
		UserDto updateduserDto=this.dao.update(userDto, userId);
		
		return ResponseEntity.ok(updateduserDto);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		
		this.dao.deleteUser(userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK); 
		
	}
	
}
