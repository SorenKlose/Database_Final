package dto;

import java.io.Serializable;

public class IngredientDTO implements Serializable, IIngredientDTO {
	private int ingredientId;
	private String ingredientName;
	private String margin;
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
	public String getMargin() {
		return margin;
	}
	@Override
	public void setMargin(String margin) {
		this.margin = margin;
	}
	@Override
	public boolean getActive(){
		return active;
	}
	@Override
	public void setActive(boolean active){this.active = active;}
}
