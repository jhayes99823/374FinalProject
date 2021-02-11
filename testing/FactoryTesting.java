package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Test;

import models.Capability;
import models.Condiment;
import models.Drink;
import models.DrinkFactory;
import recipes.AddStep;
import recipes.NullStep;
import recipes.Recipe;
import recipes.SteamStep;
import recipes.TopStep;

class FactoryTesting {

	@Test
	void testSimpleDrink() {
		String inputString = "{\r\n" + 
				"  \"order\": { \"orderID\": 2,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"200 N Main\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Espresso\"\r\n" + 
				"            }\r\n" + 
				"}";
		Drink drink = DrinkFactory.parseOrder(inputString);
		assertEquals(2, drink.getOrderID());
		assertEquals("200 N Main", drink.getAddress().getStreet());
		assertEquals(47803, drink.getAddress().getZIP());
		assertEquals("Espresso", drink.getName());
		assertFalse(drink.hasCondiments());
		Recipe recipe = new NullStep();
		assertEquals(recipe.getRecipeSteps(), drink.getRecipe().getRecipeSteps());
		assertEquals(Capability.Simple, drink.getCapabilityRequirement());
	}

	@Test
	void testAutomatedDrink() {
		String inputString = "{\r\n" + 
				"  \"order\": { \"orderID\": 1,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"200 N Main\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Americano\",\r\n" + 
				"            \"condiments\": [\r\n" + 
				"                {\"name\": \"Sugar\", \"qty\": 1},\r\n" + 
				"                {\"name\": \"Cream\", \"qty\": 2}\r\n" + 
				"            ]\r\n" + 
				"            }\r\n" + 
				"}";
		Drink drink = DrinkFactory.parseOrder(inputString);
		assertEquals(1, drink.getOrderID());
		assertEquals("200 N Main", drink.getAddress().getStreet());
		assertEquals(47803, drink.getAddress().getZIP());
		assertEquals("Americano", drink.getName());
		assertTrue(drink.hasCondiments());
		List<Condiment> condiments = new ArrayList<Condiment>();
		condiments.add(new Condiment("Sugar", 1));
		condiments.add(new Condiment("Cream", 2));
		for(int i = 0; i < condiments.size(); i++) {
			assertEquals(condiments.get(i).getName(), drink.getCondiment(i).getName());
			assertEquals(condiments.get(i).getQuantity(), drink.getCondiment(i).getQuantity());
		}
		Recipe recipe = new NullStep();
		assertEquals(recipe.getRecipeSteps(), drink.getRecipe().getRecipeSteps());
		assertEquals(Capability.Automated, drink.getCapabilityRequirement());
	}
	
	@Test
	void testProgrammableDrink() {
		String inputString = "{\r\n" + 
				"  \"order\": { \"orderID\": 4,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"3 S. Walnut\",\r\n" + 
				"                  \"ZIP\": 60601\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Latte\",\r\n" + 
				"            \"condiments\": [\r\n" + 
				"                {\"name\": \"Hazelnut\", \"qty\": 2}\r\n" + 
				"            ]\r\n" + 
				"            }\r\n" + 
				"}";
		Drink drink = DrinkFactory.parseOrder(inputString);
		assertEquals(4, drink.getOrderID());
		assertEquals("3 S. Walnut", drink.getAddress().getStreet());
		assertEquals(60601, drink.getAddress().getZIP());
		assertEquals("Latte", drink.getName());
		assertTrue(drink.hasCondiments());
		List<Condiment> condiments = new ArrayList<Condiment>();
		condiments.add(new Condiment("Hazelnut", 2));
		for(int i = 0; i < condiments.size(); i++) {
			assertEquals(condiments.get(i).getName(), drink.getCondiment(i).getName());
			assertEquals(condiments.get(i).getQuantity(), drink.getCondiment(i).getQuantity());
		}
		Recipe recipe = new NullStep();
		recipe = new SteamStep(recipe, "milk");
		recipe = new AddStep(recipe, "espresso");
		recipe = new TopStep(recipe, "whipped cream");
		assertEquals(recipe.getRecipeSteps(), drink.getRecipe().getRecipeSteps());
		assertEquals(Capability.Programmable, drink.getCapabilityRequirement());
	}

}
