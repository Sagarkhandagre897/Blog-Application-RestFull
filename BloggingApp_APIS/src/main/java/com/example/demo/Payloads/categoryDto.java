package com.example.demo.Payloads;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class categoryDto {
	
    private int category_id;
	
    @NotEmpty (message="categoryTitle must not be empty")
	private String categoryTitle;
	@NotEmpty(message="categoryDiscription must not be empty")
	private String categoryDescription;
}
