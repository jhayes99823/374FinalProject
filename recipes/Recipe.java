package recipes;

import java.util.List;

import org.json.simple.JSONObject;

public abstract class Recipe {
	public abstract List<JSONObject> addRecipeStep();
	
	public Recipe() {
		
	}
}