package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import recipes.AddStep;
import recipes.MixStep;
import recipes.NullStep;
import recipes.Recipe;
import recipes.SteamStep;
import recipes.TopStep;

class DecoratorTesting {

	@Test
	void testNullStep() {
		Recipe recipe = new NullStep();
		JSONArray expected = new JSONArray();
		assertEquals(expected, recipe.getRecipeSteps());
	}
	
	@Test
	void testAddStep() {
		Recipe recipe = new AddStep(new NullStep(), "coffee");
		JSONArray expected = new JSONArray();
		JSONObject step1 = new JSONObject();
		step1.put("commandstep", "add");
		step1.put("object", "coffee");
		expected.add(step1);
		assertEquals(expected, recipe.getRecipeSteps());
	}
	
	@Test
	void testSteamStep() {
		Recipe recipe = new SteamStep(new NullStep(), "milk");
		JSONArray expected = new JSONArray();
		JSONObject step1 = new JSONObject();
		step1.put("commandstep", "steam");
		step1.put("object", "milk");
		expected.add(step1);
		assertEquals(expected, recipe.getRecipeSteps());
	}
	
	@Test
	void testMixStep() {
		Recipe recipe = new MixStep(new NullStep());
		JSONArray expected = new JSONArray();
		JSONObject step1 = new JSONObject();
		step1.put("commandstep", "mix");
		expected.add(step1);
		assertEquals(expected, recipe.getRecipeSteps());
	}
	
	@Test
	void testTopStep() {
		Recipe recipe = new TopStep(new NullStep(), "whipped cream");
		JSONArray expected = new JSONArray();
		JSONObject step1 = new JSONObject();
		step1.put("commandstep", "top");
		step1.put("object", "whipped cream");
		expected.add(step1);
		assertEquals(expected, recipe.getRecipeSteps());
	}

	@Test
	void testLatteSteps() {
		Recipe recipe = new TopStep(new AddStep(new SteamStep(new NullStep(), "milk"), "espresso"), "whipped cream");
		JSONArray expected = new JSONArray();
		JSONObject step1 = new JSONObject();
		step1.put("commandstep", "steam");
		step1.put("object", "milk");
		expected.add(step1);
		JSONObject step2 = new JSONObject();
		step2.put("commandstep", "add");
		step2.put("object", "espresso");
		expected.add(step2);
		JSONObject step3 = new JSONObject();
		step3.put("commandstep", "top");
		step3.put("object", "whipped cream");
		expected.add(step3);
		assertEquals(expected, recipe.getRecipeSteps());
	}
}
