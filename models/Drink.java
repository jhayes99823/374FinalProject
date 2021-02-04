package models;

import recipes.Recipe;

public class Drink {

	private String name;
	private Recipe recipe;
	
	public Drink(String name, Recipe recipe) {
		this.name = name;
		this.recipe = recipe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public Capability getCapabilityRequirement() {
		return this.recipe.getCapabilityRequirement();
	}
}
