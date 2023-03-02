package com.example.demo.dao.Impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Role;
import com.example.demo.dao.RoleDao;
@Repository
public class RoleDaoImpls implements RoleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Role> createRole(Role role,int userId) {
		
		String query="CREATE TABLE IF NOT EXISTS role(role_id int primary key auto_increment,role_name varchar(50) NOT NULL,user_id_ofRole int , foreign key(user_id_ofRole) REFERENCES users(user_id))";
		
		this.jdbcTemplate.update(query);
		
		this.jdbcTemplate.update("INSERT INTO role(role_name,user_id_ofRole) VALUES(?,?)",new Object[] {role.getRole_name(),userId});
		
		List<Role> roles=this.jdbcTemplate.query("select * from role",new BeanPropertyRowMapper<Role>(Role.class));
		
		return roles;
	}

	@Override
	public Role roleByUserId(int user_id) {
		
		Role role=this.jdbcTemplate.queryForObject("select * from role where user_id_ofRole=?", new BeanPropertyRowMapper<Role>(Role.class),user_id);
		
		return role;
	}

	
	
}
