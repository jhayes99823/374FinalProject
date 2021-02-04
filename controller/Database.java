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
import recipes.MixStep;
import recipes.Recipe;
import recipes.SteamStep;
import recipes.TopStep;

public class Database {
	private static Database instance;
	
	private List<Machine> machines;
	private Map<String, List<Recipe>> drinkRecipes;

	private Database() {
		// Setup drinkRecipies
		drinkRecipes = new HashMap<String, List<Recipe>>();
		List<Recipe> latteRecipe = new ArrayList<Recipe>();
		latteRecipe.add(new SteamStep(null, "milk"));
		latteRecipe.add(new AddStep(null, "espresso"));
		latteRecipe.add(new TopStep(null, "whipped cream"));
		drinkRecipes.put("Latte", latteRecipe);
		List<Recipe> pumpkinSpiceRecipe = new ArrayList<Recipe>();
		pumpkinSpiceRecipe.add(new AddStep(null, "coffee"));
		pumpkinSpiceRecipe.add(new AddStep(null, "pumpkin spice"));
		pumpkinSpiceRecipe.add(new AddStep(null, "cream"));
		pumpkinSpiceRecipe.add(new MixStep(null));
		pumpkinSpiceRecipe.add(new TopStep(null, "nutmeg"));
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
	
	public List<Recipe> getRecipe(String drinkName) {
		return drinkRecipes.get(drinkName);
	}

}
