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
import com.example.demo.Payloads.categoryDto;
import com.example.demo.dao.categoryDao;

@RestController
@RequestMapping("/api/categories")
@Validated
public class CategoryController {
	
	@Autowired
	private categoryDao categoryDao;

	@PostMapping("/")
	public ResponseEntity<List<categoryDto>> create(@Valid @RequestBody categoryDto categoryDto){
		
		List<categoryDto> list=this.categoryDao.createCategory(categoryDto);
		
		return new ResponseEntity<List<categoryDto>>(list,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<categoryDto> update(@Valid @RequestBody categoryDto categoryDto,@PathVariable Integer catId){
		
		categoryDto updatedCategory=this.categoryDao.updateCategory(categoryDto,catId);
		
		return new ResponseEntity<categoryDto>(updatedCategory,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer catId){
		
		this.categoryDao.deleteCategory(catId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully",true),HttpStatus.OK);
		
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<categoryDto> getById(@PathVariable Integer catId){
		
		categoryDto getCategory=this.categoryDao.getCategoryById(catId);
		
		return new ResponseEntity<categoryDto>(getCategory,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<categoryDto>> getAllCategories(){
		
		List<categoryDto> list=this.categoryDao.getAllCategory();
		
		return new ResponseEntity<List<categoryDto>>(list,HttpStatus.OK);
		
	}
	
}
