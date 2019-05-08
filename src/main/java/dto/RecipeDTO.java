package dto;

import java.io.Serializable;

public class RecipeDTO implements Serializable, IRecipeDTO {

	int recipeId;
	int productId;
	int userId;
	String date;

	@Override
	public int getRecipeId() {
		return recipeId;
	}
	@Override
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	@Override
	public int getProductId() {
		return productId;
	}
	@Override
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Override
	public String getDate() {
		return date;
	}
	@Override
	public void setDate(String date) {
		this.date = date;
	}
}
