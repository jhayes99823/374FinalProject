package recipes;

import org.json.simple.JSONArray;

public class NullStep extends Recipe {

	public NullStep() {
	}

	@Override
	public JSONArray getRecipeSteps() {
		return new JSONArray();
	}

}
