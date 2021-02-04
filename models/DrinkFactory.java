package models;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.Database;
import recipes.DecoratorRecipe;
import recipes.NullStep;
import recipes.Recipe;

// recipe
// drink

// ask db for recipe steps
// parse recipe string into steps
// link recipe together
// steam -> add -> mixes -> toppings

public abstract class DrinkFactory {
	
	public static Recipe createRecipe(String drinkName, JSONArray recipeSteps) {		
		Database db = Database.getInstance();
		Recipe recipe = new NullStep();
		List<DecoratorRecipe> defaultSteamSteps = db.getRecipe(drinkName, "steam");
		for(int i = 0; i < defaultSteamSteps.size(); i++) {
			defaultSteamSteps.get(i).setPreviousRecipe(recipe);
			recipe = defaultSteamSteps.get(i);
		}
		// ADD CODE FOR PARSING STEAM RECIPE STEPS HERE
		List<DecoratorRecipe> defaultAddSteps = db.getRecipe(drinkName, "add");
		for(int i = 0; i < defaultSteamSteps.size(); i++) {
			defaultSteamSteps.get(i).setPreviousRecipe(recipe);
			recipe = defaultSteamSteps.get(i);
		}
		// ADD CODE FOR PARSING ADD RECIPE STEPS HERE
		List<DecoratorRecipe> defaultMixSteps = db.getRecipe(drinkName, "mix");
		for(int i = 0; i < defaultSteamSteps.size(); i++) {
			defaultSteamSteps.get(i).setPreviousRecipe(recipe);
			recipe = defaultSteamSteps.get(i);
		}
		// ADD CODE FOR PARSING MIX RECIPE STEPS HERE
		List<DecoratorRecipe> defaultTopSteps = db.getRecipe(drinkName, "top");
		for(int i = 0; i < defaultSteamSteps.size(); i++) {
			defaultSteamSteps.get(i).setPreviousRecipe(recipe);
			recipe = defaultSteamSteps.get(i);
		}
		// ADD CODE FOR PARSING TOP RECIPE STEPS HERE
		
//		for (int i = 0; i < recipeSteps.size(); i++) {
//			JSONObject currRecipeStep = (JSONObject) recipeSteps.get(0);
//			
////			if (db.hasRecipe(drinkName, currRecipeStep))
//		}
		
		return recipe;
	}
	
	public static Drink createDrink(JSONObject orderObj) {
		String drinkName = orderObj.get("drink").toString();
		JSONArray recipeSteps = (JSONArray) orderObj.get("Recipe");
		Drink drink = new Drink(drinkName, createRecipe(drinkName, recipeSteps));
		return drink;
	}
}
