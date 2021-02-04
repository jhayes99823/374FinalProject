package recipes;

import org.json.simple.JSONArray;

import models.Capability;

public class NullStep implements Recipe {

	public NullStep() {
	}

	@Override
	public JSONArray getRecipeSteps() {
		return new JSONArray();
	}

	@Override
	public Capability getCapabilityRequirement() {
		return Capability.Simple;
	}

}
