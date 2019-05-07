package dto;

import java.util.List;

public interface IRecipeDTO {
	int getRecipeId();

	void setRecipeId(int recipeId);

	int getProductId();

	void setProductId(int productId);

	int getUserId();

	void setUserId(int userId);

	String getDate();

	void setDate(String date);
}
