package dal;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import dto.UserDTO;

import java.util.List;

public interface IUserDAO {

	UserDTO getUser(int userId) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO user) throws DALException;
	void updateUser(UserDTO user) throws DALException;

	public class DALException extends Exception {
		//Til Java serialisering...
		private static final long serialVersionUID = 7355418246336739229L;

		public DALException(MysqlxDatatypes.Scalar.String msg, Throwable e) {
			super(msg,e);
		}

		public DALException(String msg) {
			super(msg);
		}

	}

}