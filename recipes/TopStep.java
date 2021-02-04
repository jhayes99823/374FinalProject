package recipes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TopStep extends DecoratorRecipe {

	private String ingredient;

	public TopStep(Recipe prevRecipe, String ingredient) {
		super(prevRecipe);
		this.ingredient = ingredient;
	}

	@Override
	public JSONArray getRecipeSteps() {
		// TODO Auto-generated method stub
		JSONArray completedSteps = this.prevRecipe.getRecipeSteps();

		JSONObject nextRecipeStep = new JSONObject();
		nextRecipeStep.put("commandstep", "top");
		nextRecipeStep.put("object", this.ingredient);
		completedSteps.add(nextRecipeStep);

		return completedSteps;
	}

}
