package recipes;

import java.util.List;

import org.json.simple.JSONObject;

public class TopStep extends DecoratorRecipe {

	public TopStep(Recipe prevRecipe, String ingredient) {
		super(prevRecipe, ingredient);
	}

	@Override
	public List<JSONObject> addRecipeStep() {
		// TODO Auto-generated method stub
		List<JSONObject> completedSteps = this.prevRecipe.addRecipeStep();

		JSONObject nextRecipeStep = new JSONObject();
		nextRecipeStep.put("commandstep", "top");
		nextRecipeStep.put("object", this.ingredient);

		completedSteps.add(nextRecipeStep);

		return completedSteps;
	}

}
