package models;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.Database;
import recipes.Recipe;

// recipe
// drink

// ask db for recipe steps
// parse recipe string into steps
// link recipe together
// steam -> add -> mixes -> toppings

public abstract class DrinkFactory {
	
	private static Recipe getRecipe(String drinkName, JSONArray recipeSteps) {		
		Database db = Database.getInstance();
		Recipe recipe;
				
		for (int i = 0; i < recipeSteps.size(); i++) {
			JSONObject currRecipeStep = (JSONObject) recipeSteps.get(0);
			
//			if (db.hasRecipe(drinkName, currRecipeStep))
		}
		
		return null;
	}
	
	public static Drink createDrink(JSONObject orderObj) {
		String drinkName = orderObj.get("drink").toString();
		JSONArray recipeSteps = (JSONArray) orderObj.get("Recipe");
		Drink drink = new Drink(drinkName, getRecipe(drinkName, recipeSteps));
		return drink;
	}
}
