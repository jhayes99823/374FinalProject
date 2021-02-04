package models;

import org.json.simple.JSONObject;

import recipes.NullStep;
import recipes.Recipe;

public class Drink {

	private String name;
	private Recipe recipe;
	
	public Drink(String name, Recipe recipe) {
		this.name = name;
		this.recipe = recipe;
	}
	
	public Drink(String name) {
		this.name = name;
		this.recipe = new NullStep();
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
	
	public JSONObject toJSON() {
		JSONObject drinkJSON = new JSONObject();
		drinkJSON.put("drink", name);
		drinkJSON.put("recipe", recipe.getRecipeSteps());
		return drinkJSON;
	}
}
