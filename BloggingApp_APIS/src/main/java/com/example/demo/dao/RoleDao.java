package com.example.demo.dao;

import java.util.List;

import com.example.demo.Entity.Role;

public interface RoleDao {

	List<Role> createRole(Role role,int userId);
	
	Role roleByUserId(int user_id);
}
