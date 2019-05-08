package dto;

import java.io.Serializable;
import java.util.List;

public class RecipeDTO implements Serializable, IRecipeDTO {

	private int recipeId;
	private int productId;
	private String date;
	private List<Integer> pharmaList;

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
	@Override
	public List<Integer> getPharmaList(){
		return pharmaList;
	}
}
