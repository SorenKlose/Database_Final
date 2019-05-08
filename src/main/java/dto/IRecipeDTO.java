package dto;

import java.util.List;

public interface IRecipeDTO {
	int getRecipeId();

	void setRecipeId(int recipeId);

	int getProductId();

	void setProductId(int productId);

	double getAmount();

	void setAmount(double amount);

	String getDate();

	void setDate(String date);

	List<Integer> getPharmaList();

	List<Integer> getIngList();
}
