package models;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import recipes.NullStep;
import recipes.Recipe;

public class Drink {
	private int orderID;
	private Address address;
	private String name;
	private List<Condiment> condiments;
	private Recipe recipe;

	public Drink(int orderID, Address address, String name, List<Condiment> condiments, Recipe recipe) {
		this.orderID = orderID;
		this.address = address;
		this.name = name;
		this.condiments = condiments;
		this.recipe = recipe;
	}

	public Drink(int orderID, Address address, String name, List<Condiment> condiments) {
		this.orderID = orderID;
		this.address = address;
		this.name = name;
		this.condiments = condiments;
		this.recipe = new NullStep();
	}
	
	public Drink(int orderID, Address address, String name, Recipe recipe) {
		this.orderID = orderID;
		this.address = address;
		this.name = name;
		this.condiments = new ArrayList<Condiment>();
		this.recipe = recipe;
	}
	
	public Drink(int orderID, Address address, String name) {
		this.orderID = orderID;
		this.address = address;
		this.name = name;
		this.condiments = new ArrayList<Condiment>();
		this.recipe = new NullStep();
	}
	
	public void incrementOrderID() {
		orderID++;
	}

	public int getOrderID() {
		return this.orderID;
	}

	public Address getAddress() {
		return this.address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Condiment> getCondiments() {
		return this.condiments;
	}

	public boolean hasCondiments() {
		return this.condiments.size() != 0;
	}

	public Condiment getCondiment(int index) {
		return this.condiments.get(index);
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Capability getCapabilityRequirement() {
		if (recipe.getCapabilityRequirement() == Capability.Programmable) {
			return Capability.Programmable;
		} else if (!condiments.isEmpty()) {
			return Capability.Automated;
		} else {
			return Capability.Simple;
		}
	}

	public JSONObject toJSON() {
		JSONObject drinkJSON = new JSONObject();
		drinkJSON.put("orderID", orderID);
		drinkJSON.put("address", address.toJSON());
		drinkJSON.put("drink", name);
		drinkJSON.put("condiments", condimentsToJSON());
		drinkJSON.put("recipe", recipe.getRecipeSteps());
		return drinkJSON;
	}
	
	private JSONArray condimentsToJSON() {
		JSONArray condimentsArray = new JSONArray();
		for(Condiment condiment : condiments) {
			condimentsArray.add(condiment.toJSON());
		}
		return condimentsArray;
	}
	
	public boolean Equals(Drink drink) {
		return  this.toJSON().toString().equals(drink.toJSON().toString());
	}
}
