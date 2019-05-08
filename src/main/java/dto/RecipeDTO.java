package dto;

import java.io.Serializable;
import java.util.List;

public class RecipeDTO implements Serializable, IRecipeDTO {

	private int recipeId;
	private int productId;
	private double amount;
	private String date;
	private List<Integer> pharmaList;
	private List<Integer> ingList;

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
	public double getAmount(){
		return amount;
	}
	@Override
	public void setAmount(double amount){
		this.amount = amount;
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
	@Override
	public List<Integer> getIngList(){
		return ingList;
	}
}
