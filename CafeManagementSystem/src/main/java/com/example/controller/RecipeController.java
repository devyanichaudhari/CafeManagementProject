package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Recipe;
import com.example.entity.User;
import com.example.service.RecipeService;
import com.example.service.UserService;

@RestController
public class RecipeController 
{
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private UserService userService;
	@PostMapping("/api/recipe/users/{userId}")
	public Recipe createRecipe(@RequestBody Recipe recipe,@PathVariable Long userId) throws Exception
	{
		User user=userService.findUserById(userId);
		Recipe createdRecipe=recipeService.createRecipe(recipe, user);
		return createdRecipe;
	}
	@GetMapping("/api/recipe")
	public List<Recipe> getAllRecipe() 
	{
		List<Recipe> recipes = recipeService.findAllRecipe();
		return recipes;
	}
	@DeleteMapping("/api/recipe/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId) throws Exception
	{
		recipeService.deleteRecipe(recipeId);
		return "Recipe Deleted Successfully";
	}
	@PutMapping("/api/recipe/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe,@PathVariable Long id) throws Exception
	{
		Recipe updatedRecipe=recipeService.updateRecipe(recipe, id);
		return updatedRecipe;
	}
	@PutMapping("/api/recipe/{recipeId}/users/{userId}")
	public Recipe likeRecipe(@PathVariable Long recipeId,@PathVariable Long userId) throws Exception
	{
		User user=userService.findUserById(userId);
		Recipe likedRecipe=recipeService.likeRecipe(recipeId, user);
		return likedRecipe;
	}
}
