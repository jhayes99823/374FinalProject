package recipes;

import org.json.simple.JSONArray;

import models.Capability;

public interface Recipe {
	public JSONArray getRecipeSteps();
	public Capability getCapabilityRequirement();
}