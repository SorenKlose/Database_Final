package dal;

import dto.IIngredientDTO;

import java.util.List;

public interface IIngredientDAO {
	//Create
	void createIngredient(IIngredientDTO ingredient) throws DALException;

	//Read
	IIngredientDTO getIngredient(int ingredientId) throws DALException;
	List<IIngredientDTO> getIngredientList() throws DALException;

	//Update
	void updateIngredient(IIngredientDTO ingredient) throws DALException;

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
