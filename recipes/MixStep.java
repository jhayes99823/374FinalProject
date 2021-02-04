package recipes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MixStep extends DecoratorRecipe {

	public MixStep(Recipe prevRecipe) {
		super(prevRecipe);
	}

	@Override
	public JSONArray getRecipeSteps() {
		JSONArray completedSteps = this.prevRecipe.getRecipeSteps();

		JSONObject nextRecipeStep = new JSONObject();
		nextRecipeStep.put("commandstep", "mix");

		completedSteps.add(nextRecipeStep);

		return completedSteps;
	}

}
