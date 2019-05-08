package dal;

import dto.IRecipeDTO;
import java.util.List;

public interface IRecipeDAO {
	//Create
	void createRecipe(IRecipeDTO recipe) throws DALException;

	//Read
	IRecipeDTO getRecipe(int recipeId) throws DALException;
	List<IRecipeDTO> getRecipeList() throws DALException;

	//Update
	void updateRecipe(IRecipeDTO recipe) throws DALException;

	public class DALException extends Exception {
		//Til Java serialisering...
		private static final long serialVersionUID = 7355418246336739229L;

		public DALException(String msg, Throwable e) {
			super(msg,e);
		}

		public DALException(String msg) {
			super(msg);
		}

	}
}
