package recipes;

public abstract class DecoratorRecipe extends Recipe {
	protected Recipe prevRecipe;
	protected String ingredient;

	public DecoratorRecipe(Recipe prevRecipe) {
		this.prevRecipe = prevRecipe;
	}

	public void setPreviousRecipe(Recipe recipe) {
		this.prevRecipe = recipe;
	}
}
