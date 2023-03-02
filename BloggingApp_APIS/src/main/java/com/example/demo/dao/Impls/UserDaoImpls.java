package com.example.demo.dao.Impls;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.User;
import com.example.demo.Payloads.UserDto;

import com.example.demo.dao.UserDao;

@Repository
public class UserDaoImpls implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public  List<UserDto> create(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		
		String query1="CREATE TABLE IF NOT EXISTS USERS(user_id int primary key auto_increment, name varchar(20) NOT NULL, email varchar(50) unique NOT NULL,password varchar(50) NOT NULL,about varchar(500) NOT NULL)";
		 this.jdbcTemplate.update(query1);
		 
		 
		 this.jdbcTemplate.update("INSERT INTO USERS(name,email,password,about) VALUES(?,?,?,?)", new Object[] {user.getName(),user.getEmail(),user.getPassword(),user.getAbout()});
		 
		   List<UserDto> res = this.jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<UserDto>(UserDto.class));
			return res;
	}

	@Override
	public UserDto update(UserDto userDto, int user_id) {
		// TODO Auto-generated method stub
		
		User user=dtoToUser(userDto);
		
		this.jdbcTemplate.update("update users set name=?,email=?,password=?,about=? where user_id=?", new Object[] {user.getName(),user.getEmail(),user.getPassword(),user.getAbout(),user_id});
;
		
		UserDto updatedUser=this.jdbcTemplate.queryForObject("select * from users where user_id=?",  new BeanPropertyRowMapper<UserDto>(UserDto.class),user_id);
		
		return updatedUser;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		UserDto userDto=this.jdbcTemplate.queryForObject("select * from users where user_id=?", new BeanPropertyRowMapper<UserDto>(UserDto.class),userId);
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		 List<UserDto> res = this.jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<UserDto>(UserDto.class));
		
		return res;
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
		this.jdbcTemplate.update("delete from users where user_id=?",userId);
		
		
	}
	
	


public User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
        return user;		
	}
	
	public UserDto userToDto(User user) {
		
		UserDto userDto= this.modelMapper.map(user, UserDto.class);
		
		return userDto;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		
		System.out.println("inside dao layer");
		
		User user=this.jdbcTemplate.queryForObject("select * from users where email=?",new BeanPropertyRowMapper<User>(User.class), email);
		
		return user;
	}

	
	
}
