package dto;

import java.io.Serializable;

public class IngredientDTO implements Serializable, IIngredientDTO {
	private int ingredientId;
	private String ingredientName;
	private double margin;
	private boolean active;
	@Override
	public int getIngredientId(){
		return ingredientId;
	}
	@Override
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	@Override
	public String getIngredientName() {
		return ingredientName;
	}
	@Override
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	@Override
	public double getMargin() {
		return margin;
	}
	@Override
	public void setMargin(double margin) {
		this.margin = margin;
	}
	@Override
	public boolean getActive(){
		return active;
	}
	@Override
	public void setActive(boolean active){this.active = active;}
	@Override
	public String toString(){
		return "" + ingredientId + ingredientName + margin + active;
	}
}
