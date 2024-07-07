package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Recipe;
import com.example.entity.User;
import com.example.service.RecipeService;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController 
{
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private UserService userService;
	@PostMapping()
	public Recipe createRecipe(@RequestBody Recipe recipe,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User user=userService.findUserByJwt(jwt);
		Recipe createdRecipe=recipeService.createRecipe(recipe, user);
		return createdRecipe;
	}
	@GetMapping()
	public List<Recipe> getAllRecipe() 
	{
		List<Recipe> recipes = recipeService.findAllRecipe();
		return recipes;
	}
	@DeleteMapping("/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId) throws Exception
	{
		recipeService.deleteRecipe(recipeId);
		return "Recipe Deleted Successfully";
	}
	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe,@PathVariable Long id) throws Exception
	{
		Recipe updatedRecipe=recipeService.updateRecipe(recipe, id);
		return updatedRecipe;
	}
	@PutMapping("/{recipeId}/like")
	public Recipe likeRecipe(@PathVariable Long recipeId,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User user=userService.findUserByJwt(jwt);
		Recipe likedRecipe=recipeService.likeRecipe(recipeId, user);
		return likedRecipe;
	}
}
