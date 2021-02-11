package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import controller.Database;
import recipes.AddStep;
import recipes.DecoratorRecipe;
import recipes.MixStep;
import recipes.NullStep;
import recipes.Recipe;
import recipes.SteamStep;
import recipes.TopStep;

// recipe
// drink

// ask db for recipe steps
// parse recipe string into steps
// link recipe together
// steam -> add -> mixes -> toppings

public abstract class DrinkFactory {

	private static final String ORDER_KEY = "order";
	
	private static final String DRINK_ORDER_ID_KEY = "orderID";
	private static final String DRINK_ADDRESS_KEY = "address";
	private static final String DRINK_NAME_KEY = "drink";
	private static final String DRINK_CONDIMENTS_KEY = "condiments";
	private static final String DRINK_RECIPE_KEY = "recipe";

	private static final String ADDRESS_STREET_KEY = "street";
	private static final String ADDRESS_ZIP_KEY = "ZIP";

	private static final String CONDIMENT_NAME_KEY = "name";
	private static final String CONDIMENT_QUANTITY_KEY = "qty";

	private static final String RECIPE_TYPE_KEY = "commandstep";
	private static final String RECIPE_OBJECT_KEY = "object";

	private static final String RECIPE_TYPE_STEAM_VALUE = "steam";
	private static final String RECIPE_TYPE_ADD_VALUE = "add";
	private static final String RECIPE_TYPE_MIX_VALUE = "mix";
	private static final String RECIPE_TYPE_TOP_VALUE = "top";

	public static Drink parseOrder(String orderString) {
		JSONObject orderJSON = (JSONObject) JSONValue.parse(orderString);
		JSONObject drinkJSON = (JSONObject) orderJSON.get(ORDER_KEY);
		return createDrink(drinkJSON);
	}
	
	public static Drink createDrink(JSONObject drinkJSON) {
		int orderID = ((Long) drinkJSON.get(DRINK_ORDER_ID_KEY)).intValue();
		JSONObject addressJSON = (JSONObject) drinkJSON.get(DRINK_ADDRESS_KEY);
		Address address = DrinkFactory.createAddress(addressJSON);
		String name = (String) drinkJSON.get(DRINK_NAME_KEY);
		JSONArray condimentsArray = (JSONArray) drinkJSON.get(DRINK_CONDIMENTS_KEY);
		List<Condiment> condiments = DrinkFactory.createCondiments(condimentsArray);
		JSONArray recipeSteps = (JSONArray) drinkJSON.get(DRINK_RECIPE_KEY);
		Recipe recipe = DrinkFactory.createRecipe(name, recipeSteps);
		return new Drink(orderID, address, name, condiments, recipe);
	}

	public static Address createAddress(JSONObject addressJSON) {
		String street = (String) addressJSON.get(ADDRESS_STREET_KEY);
		int ZIP = ((Long) addressJSON.get(ADDRESS_ZIP_KEY)).intValue();
		return new Address(street, ZIP);
	}

	public static List<Condiment> createCondiments(JSONArray condimentsArray) {
		if(condimentsArray==null) {
			return new ArrayList<Condiment>();
		}
		List<Condiment> condiments = new ArrayList<Condiment>(condimentsArray.size());
		for (int i = 0; i < condimentsArray.size(); i++) {
			JSONObject condimentJSON = (JSONObject) condimentsArray.get(i);
			Condiment condiment = DrinkFactory.createCondiment(condimentJSON);
			condiments.add(condiment);
		}
		return condiments;
	}

	public static Condiment createCondiment(JSONObject condimentJSON) {
		String name = (String) condimentJSON.get(CONDIMENT_NAME_KEY);
		int quantity = ((Long) condimentJSON.get(CONDIMENT_QUANTITY_KEY)).intValue();
		return new Condiment(name, quantity);
	}

	public static Recipe createRecipe(String drinkName, JSONArray recipeSteps) {
		Database db = Database.getInstance();
		Recipe recipe = new NullStep();
		Map<String, List<DecoratorRecipe>> recipeTypeMap = parseRecipes(recipeSteps);
		List<DecoratorRecipe> defaultSteamSteps = db.getRecipe(drinkName, RECIPE_TYPE_STEAM_VALUE);
		for (int i = 0; i < defaultSteamSteps.size(); i++) {
			defaultSteamSteps.get(i).setPreviousRecipe(recipe);
			recipe = defaultSteamSteps.get(i);
		}
		if (recipeTypeMap.containsKey(RECIPE_TYPE_STEAM_VALUE)) {
			List<DecoratorRecipe> newSteamSteps = recipeTypeMap.get(RECIPE_TYPE_STEAM_VALUE);
			for (int i = 0; i < newSteamSteps.size(); i++) {
				newSteamSteps.get(i).setPreviousRecipe(recipe);
				recipe = newSteamSteps.get(i);
			}
		}
		List<DecoratorRecipe> defaultAddSteps = db.getRecipe(drinkName, RECIPE_TYPE_ADD_VALUE);
		for (int i = 0; i < defaultAddSteps.size(); i++) {
			defaultAddSteps.get(i).setPreviousRecipe(recipe);
			recipe = defaultAddSteps.get(i);
		}
		if (recipeTypeMap.containsKey(RECIPE_TYPE_ADD_VALUE)) {
			List<DecoratorRecipe> newAddSteps = recipeTypeMap.get(RECIPE_TYPE_ADD_VALUE);
			for (int i = 0; i < newAddSteps.size(); i++) {
				newAddSteps.get(i).setPreviousRecipe(recipe);
				recipe = newAddSteps.get(i);
			}
		}
		List<DecoratorRecipe> defaultMixSteps = db.getRecipe(drinkName, RECIPE_TYPE_MIX_VALUE);
		for (int i = 0; i < defaultMixSteps.size(); i++) {
			defaultMixSteps.get(i).setPreviousRecipe(recipe);
			recipe = defaultMixSteps.get(i);
		}
		if (recipeTypeMap.containsKey(RECIPE_TYPE_MIX_VALUE)) {
			List<DecoratorRecipe> newMixSteps = recipeTypeMap.get(RECIPE_TYPE_MIX_VALUE);
			for (int i = 0; i < newMixSteps.size(); i++) {
				newMixSteps.get(i).setPreviousRecipe(recipe);
				recipe = newMixSteps.get(i);
			}
		}
		List<DecoratorRecipe> defaultTopSteps = db.getRecipe(drinkName, RECIPE_TYPE_TOP_VALUE);
		for (int i = 0; i < defaultTopSteps.size(); i++) {
			defaultTopSteps.get(i).setPreviousRecipe(recipe);
			recipe = defaultTopSteps.get(i);
		}
		if (recipeTypeMap.containsKey(RECIPE_TYPE_TOP_VALUE)) {
			List<DecoratorRecipe> newTopSteps = recipeTypeMap.get(RECIPE_TYPE_TOP_VALUE);
			for (int i = 0; i < newTopSteps.size(); i++) {
				newTopSteps.get(i).setPreviousRecipe(recipe);
				recipe = newTopSteps.get(i);
			}
		}
		return recipe;
	}

	private static Map<String, List<DecoratorRecipe>> parseRecipes(JSONArray recipeArray) {
		if(recipeArray == null) {
			return new HashMap<String, List<DecoratorRecipe>>();
		}
		Map<String, List<DecoratorRecipe>> recipeTypeMap = new HashMap<String, List<DecoratorRecipe>>();
		for (int i = 0; i < recipeArray.size(); i++) {
			JSONObject recipeStep = (JSONObject) recipeArray.get(i);
			String stepType = (String) recipeStep.get(RECIPE_TYPE_KEY);
			String object = null;
			DecoratorRecipe step = null;
			if (stepType.equals(RECIPE_TYPE_STEAM_VALUE)) {
				object = (String) recipeStep.get(RECIPE_OBJECT_KEY);
				step = new SteamStep(null, object);
			} else if (stepType.equals(RECIPE_TYPE_ADD_VALUE)) {
				object = (String) recipeStep.get(RECIPE_OBJECT_KEY);
				step = new AddStep(null, object);
			} else if (stepType.equals(RECIPE_TYPE_MIX_VALUE)) {
				step = new MixStep(null);
			} else if (stepType.equals(RECIPE_TYPE_TOP_VALUE)) {
				object = (String) recipeStep.get(RECIPE_OBJECT_KEY);
				step = new TopStep(null, object);
			} else {
				continue;
			}
			if (!recipeTypeMap.containsKey(stepType)) {
				recipeTypeMap.put(stepType, new ArrayList<DecoratorRecipe>());
			}
			recipeTypeMap.get(stepType).add(step);
		}
		return recipeTypeMap;
	}
}
