package recipes;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class NullStep extends Recipe {

	public NullStep() {
		super();
	}

	public List<JSONObject> addRecipeStep() {

		return new ArrayList<JSONObject>();
	}

}
