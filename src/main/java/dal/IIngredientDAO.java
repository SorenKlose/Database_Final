package dal;

import dto.IIngredientDTO;

import java.util.List;

public interface IIngredientDAO {
	//Create
	void createIngredient(IIngredientDTO user) throws IIngredientDAO.DALException;
	//Read
	IIngredientDTO getIngredient(int userId) throws IIngredientDAO.DALException;
	List<IIngredientDTO> getIngredientList() throws IIngredientDAO.DALException;
	//Update
	void updateIngredient(IIngredientDTO user) throws IIngredientDAO.DALException;

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
