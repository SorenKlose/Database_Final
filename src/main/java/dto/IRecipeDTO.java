package dto;

import java.sql.Date;
import java.util.List;

public interface IRecipeDTO {
	int getRecipeId();

	void setRecipeId(int recipeId);

	int getProductId();

	void setProductId(int productId);

	double getAmount();

	void setAmount(double amount);

	Date getDate();

	void setDate(Date date);

	List<Integer> getPharmaList();

	List<Integer> getIngList();
}
