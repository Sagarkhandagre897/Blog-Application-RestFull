package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.User;
import com.example.demo.Payloads.UserDto;

public interface UserDao {

    List<UserDto> create(UserDto userDto);
	UserDto update(UserDto userDto,int user_id);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(int userId);
	
	User findByEmail(String email);
	
}
