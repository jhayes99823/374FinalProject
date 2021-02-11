package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Address;
import models.Capability;
import models.Controller;
import models.ControllerType;
import models.Machine;
import recipes.AddStep;
import recipes.DecoratorRecipe;
import recipes.MixStep;
import recipes.SteamStep;
import recipes.TopStep;

public class Database {
	private static Database instance;

	private List<Machine> machines;
	private Map<String, Map<String, List<DecoratorRecipe>>> drinkRecipes;

	private Database() {
		// Setup drinkRecipies
		drinkRecipes = new HashMap<String, Map<String, List<DecoratorRecipe>>>();
		Map<String, List<DecoratorRecipe>> latteRecipe = new HashMap<String, List<DecoratorRecipe>>();
		List<DecoratorRecipe> latteRecipeSteam = new ArrayList<DecoratorRecipe>();
		latteRecipeSteam.add(new SteamStep(null, "milk"));
		latteRecipe.put("steam", latteRecipeSteam);
		List<DecoratorRecipe> latteRecipeAdd = new ArrayList<DecoratorRecipe>();
		latteRecipeAdd.add(new AddStep(null, "espresso"));
		latteRecipe.put("add", latteRecipeAdd);
		List<DecoratorRecipe> latteRecipeTop = new ArrayList<DecoratorRecipe>();
		latteRecipeTop.add(new TopStep(null, "whipped cream"));
		latteRecipe.put("top", latteRecipeTop);
		drinkRecipes.put("Latte", latteRecipe);
		Map<String, List<DecoratorRecipe>> pumpkinSpiceRecipe = new HashMap<String, List<DecoratorRecipe>>();
		List<DecoratorRecipe> pumpkinSpiceRecipeAdd = new ArrayList<DecoratorRecipe>();
		pumpkinSpiceRecipeAdd.add(new AddStep(null, "coffee"));
		pumpkinSpiceRecipeAdd.add(new AddStep(null, "pumpkin spice"));
		pumpkinSpiceRecipeAdd.add(new AddStep(null, "cream"));
		pumpkinSpiceRecipe.put("add", pumpkinSpiceRecipeAdd);
		List<DecoratorRecipe> pumpkinSpiceRecipeMix = new ArrayList<DecoratorRecipe>();
		pumpkinSpiceRecipeMix.add(new MixStep(null));
		pumpkinSpiceRecipe.put("mix", pumpkinSpiceRecipeMix);
		List<DecoratorRecipe> pumpkinSpiceRecipeTop = new ArrayList<DecoratorRecipe>();
		pumpkinSpiceRecipeTop.add(new TopStep(null, "nutmeg"));
		pumpkinSpiceRecipe.put("top", pumpkinSpiceRecipeTop);
		drinkRecipes.put("Pumpkin Spice", pumpkinSpiceRecipe);
		// Setup controllers
		Controller controller1 = new Controller(1, ControllerType.Advanced, new Address("200 N Main", 47803));
		Controller controller2 = new Controller(2, ControllerType.Advanced, new Address("3 S. Walnut", 60601));
		// Setup machines
		machines = new ArrayList<Machine>();
		List<Capability> machine1Capabilities = new ArrayList<Capability>();
		machine1Capabilities.add(Capability.Simple);
		machine1Capabilities.add(Capability.Automated);
		Machine machine1 = new Machine(1, controller1, machine1Capabilities);
		machines.add(machine1);
		List<Capability> machine2Capabilities = new ArrayList<Capability>();
		machine2Capabilities.add(Capability.Simple);
		machine2Capabilities.add(Capability.Automated);
		machine2Capabilities.add(Capability.Programmable);
		Machine machine2 = new Machine(2, controller1, machine2Capabilities);
		machines.add(machine2);
		List<Capability> machine3Capabilities = new ArrayList<Capability>();
		machine3Capabilities.add(Capability.Simple);
		machine3Capabilities.add(Capability.Automated);
		machine2Capabilities.add(Capability.Programmable);
		Machine machine3 = new Machine(3, controller2, machine3Capabilities);
		machines.add(machine3);
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public boolean hasRecipe(String drinkName) {
		return drinkRecipes.containsKey(drinkName);
	}

	public boolean hasRecipe(String drinkName, String stepName) {
		return hasRecipe(drinkName) && drinkRecipes.get(drinkName).containsKey(stepName);
	}

	public List<DecoratorRecipe> getRecipe(String drinkName, String stepName) {
		if (!hasRecipe(drinkName, stepName)) {
			return new ArrayList<DecoratorRecipe>();
		} else {
			return drinkRecipes.get(drinkName).get(stepName);
		}
	}

}
