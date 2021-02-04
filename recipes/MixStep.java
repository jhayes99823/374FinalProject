package recipes;

import java.util.List;

import org.json.simple.JSONObject;

public class MixStep extends DecoratorRecipe {

	public MixStep(Recipe prevRecipe, String ingredient) {
		super(prevRecipe, null);
	}

	@Override
	public List<JSONObject> addRecipeStep() {
		List<JSONObject> completedSteps = this.prevRecipe.addRecipeStep();

		JSONObject nextRecipeStep = new JSONObject();
		nextRecipeStep.put("commandstep", "mix");

		completedSteps.add(nextRecipeStep);

		return completedSteps;
	}

}
