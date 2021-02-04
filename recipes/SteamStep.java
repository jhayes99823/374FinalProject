package recipes;

import java.util.List;

import org.json.simple.JSONObject;

public class SteamStep extends DecoratorRecipe {
	
	public SteamStep(Recipe prevRecipe, String ingredient) {
		super(prevRecipe, ingredient);
	}
	@Override
	public List<JSONObject> addRecipeStep() {
		// TODO Auto-generated method stub
		List<JSONObject> completedSteps = this.prevRecipe.addRecipeStep();

		JSONObject nextRecipeStep = new JSONObject();
		nextRecipeStep.put("commandstep", "steam");
		nextRecipeStep.put("object", this.ingredient);

		completedSteps.add(nextRecipeStep);

		return completedSteps;
	}

}
