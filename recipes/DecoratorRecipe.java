package recipes;

public abstract class DecoratorRecipe extends Recipe {
	protected Recipe prevRecipe;
	protected String ingredient;

	public DecoratorRecipe(Recipe prevRecipe, String ingredient) {
		this.prevRecipe = prevRecipe;
		this.ingredient = ingredient;
	}

	public void setPreviousRecipe(Recipe recipe) {
		this.prevRecipe = recipe;
	}
}
