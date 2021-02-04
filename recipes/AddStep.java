package recipes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AddStep extends DecoratorRecipe {

	private String ingredient;
	
	public AddStep(Recipe prevRecipe, String ingredient) {
		super(prevRecipe);
		this.ingredient = ingredient;
	}

	@Override
	public JSONArray getRecipeSteps() {
		JSONArray completedSteps = this.prevRecipe.getRecipeSteps();

		JSONObject nextRecipeStep = new JSONObject();
		nextRecipeStep.put("commandstep", "add");
		nextRecipeStep.put("object", this.ingredient);
		completedSteps.add(nextRecipeStep);

		return completedSteps;
	}

}
