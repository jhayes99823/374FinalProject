package models;

import java.util.List;

import org.json.simple.JSONObject;

import controller.Database;
import recipes.Recipe;

public abstract class DrinkFactory {
	public static Recipe getRecipe(JSONObject drinkObj) {
		List<Recipe> recipes = Database.getInstance().getRecipe(drinkObj.get("drink"));
		
		return null;
	}
}
