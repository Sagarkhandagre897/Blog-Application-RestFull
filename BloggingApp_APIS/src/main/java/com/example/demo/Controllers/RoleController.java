package com.example.demo.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Role;
import com.example.demo.dao.RoleDao;

@RestController
@RequestMapping("/api")
@Validated
public class RoleController {
	
	@Autowired
	private RoleDao roleDao;

	@PostMapping("/role/userId/{userId}")
	public ResponseEntity<List<Role>> create(@Valid @RequestBody Role role , @PathVariable int userId){
		
		List<Role> roles=this.roleDao.createRole(role,userId);
		
		return new ResponseEntity<List<Role>>(roles,HttpStatus.CREATED);
		
	}
	
}
