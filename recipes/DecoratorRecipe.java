package recipes;

import models.Capability;

public abstract class DecoratorRecipe implements Recipe {
	protected Recipe prevRecipe;
	protected String ingredient;

	public DecoratorRecipe(Recipe prevRecipe) {
		this.prevRecipe = prevRecipe;
	}

	public void setPreviousRecipe(Recipe recipe) {
		this.prevRecipe = recipe;
	}
	
	@Override
	public Capability getCapabilityRequirement() {
		return Capability.Programmable;
	}
}
