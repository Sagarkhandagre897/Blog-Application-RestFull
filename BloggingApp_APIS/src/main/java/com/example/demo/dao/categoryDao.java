package com.example.demo.dao;

import java.util.List;

import com.example.demo.Entity.Post;
import com.example.demo.Payloads.categoryDto;

public interface categoryDao {

   List<categoryDto> createCategory(categoryDto categoryDto);
   
   categoryDto updateCategory(categoryDto categoryDto,int categoryId);
   
   void deleteCategory(Integer categoryId);
   
   categoryDto getCategoryById(Integer categoryId);
   
   List<categoryDto> getAllCategory();
   

}
