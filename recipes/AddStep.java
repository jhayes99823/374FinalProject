package recipes;

import java.util.List;

import org.json.simple.JSONObject;

public class AddStep extends DecoratorRecipe {

	public AddStep(Recipe prevRecipe, String ingredient) {
		super(prevRecipe, ingredient);
	}

	@Override
	public List<JSONObject> addRecipeStep() {
		List<JSONObject> completedSteps = this.prevRecipe.addRecipeStep();

		JSONObject nextRecipeStep = new JSONObject();
		nextRecipeStep.put("commandstep", "add");
		nextRecipeStep.put("object", this.ingredient);

		completedSteps.add(nextRecipeStep);

		return completedSteps;
	}

}
