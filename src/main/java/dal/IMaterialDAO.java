package dal;

import dto.IMaterialDTO;

import java.util.List;

public interface IMaterialDAO {
	//
	void createMaterial(IMaterialDTO material) throws DALException;
	//Read
	IMaterialDTO getMaterial(int materialId) throws DALException;

	List<IMaterialDTO> getMaterialList() throws DALException;
	//Update
	void updateMaterial(IMaterialDTO material) throws DALException;

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
