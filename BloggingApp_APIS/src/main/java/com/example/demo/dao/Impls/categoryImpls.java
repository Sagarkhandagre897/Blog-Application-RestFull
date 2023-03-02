package com.example.demo.dao.Impls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Category;
import com.example.demo.Payloads.categoryDto;
import com.example.demo.dao.categoryDao;

@Repository
public class categoryImpls implements categoryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<categoryDto> createCategory(categoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category category= this.modelMapper.map(categoryDto,Category.class);
		
		 
		 String query2="CREATE TABLE IF NOT EXISTS category(category_id int primary key auto_increment,categoryTitle varchar(100) NOT NULL,categoryDescription varchar(500) NOT NULL)"; 
			this.jdbcTemplate.update(query2);
			
		
		this.jdbcTemplate.update("INSERT INTO category(categoryTitle,categoryDescription) values(?,?)",new Object[]{category.getCategoryTitle(),category.getCategoryDescription()});
		
		List<categoryDto> list= this.jdbcTemplate.query("select * from category",new BeanPropertyRowMapper<categoryDto>(categoryDto.class));
		
		return list;
	}

	@Override
	public categoryDto updateCategory(categoryDto categoryDto, int categoryId) {
		// TODO Auto-generated method stub
		
		Category category= this.modelMapper.map(categoryDto,Category.class);
		
		this.jdbcTemplate.update("update category set categoryTitle=?,categoryDescription=? where category_id=?",new Object[] {category.getCategoryTitle(),category.getCategoryDescription(),categoryId});
		
		categoryDto updatedCategory=this.jdbcTemplate.queryForObject("select * from category where category_id=?", new BeanPropertyRowMapper<categoryDto>(categoryDto.class),categoryId);
		
		return updatedCategory;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		this.jdbcTemplate.update("delete from category where category_id=?",categoryId);
		
	}

	@Override
	public categoryDto getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		
		categoryDto getCategory=this.jdbcTemplate.queryForObject("select * from category where category_id=?", new BeanPropertyRowMapper<categoryDto>(categoryDto.class),categoryId);
		
		return getCategory;
		
	}

	@Override
	public List<categoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		
		List<categoryDto> list=this.jdbcTemplate.query("select * from category", new BeanPropertyRowMapper<categoryDto>(categoryDto.class));
		
		return list;
	}

	
}
