package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserDAO implements IUserDAO {

	private Connection createConnection() throws DALException {
		try {
			return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185086?"
					+ "user=s185086&password=PL9404AoCEaBAFfUjd9dG");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public UserDTO getUser(int userId) throws DALException {
		Connection c = createConnection();
		UserDTO user = new UserDTO();

		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM userlist WHERE userID = " + userId);
			rs.next();

			String roles = rs.getString("roles");
			String[] rolesArray = roles.split(",");
			List<String> rolesList = new ArrayList<String>(Arrays.asList(rolesArray));

			user.setUserId(rs.getInt("userID"));
			user.setUserName(rs.getString("userName"));
			user.setIni(rs.getString("ini"));
			user.setRoles(rolesList);

			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return user;
	}



	@Override
	public List<UserDTO> getUserList() throws DALException {
		Connection c = createConnection();
		UserDTO user = new UserDTO();
		List<UserDTO> userList = new ArrayList<UserDTO>();

		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM userlist");

			while (rs.next())
			{
				String roles = rs.getString("roles");
				String[] rolesArray = roles.split(",");
				List<String> rolesList = new ArrayList<String>(Arrays.asList(rolesArray));

				user.setUserId(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setIni(rs.getString("ini"));
				user.setRoles(rolesList);
				userList.add(user);
			}
			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return userList;
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		Connection c = createConnection();

		try {
			Statement st = c.createStatement();
			int userId = user.getUserId();
			String userName = user.getUserName();
			String ini = user.getIni();
			String roles = String.join(",", user.getRoles());

			st.executeUpdate("INSERT INTO userlist VALUES(" + userId + ", '" + userName + "', '" + ini + "', '" + roles + "')");

			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		Connection c = createConnection();

		try {
			Statement st = c.createStatement();
			int userId = user.getUserId();
			String userName = user.getUserName();
			String ini = user.getIni();
			String roles = String.join(",", user.getRoles());

			st.executeUpdate("UPDATE userlist SET userName = " + userName +" WHERE userID = " + userId);
			st.executeUpdate("UPDATE userlist SET ini = " + ini +" WHERE userID = " + userId);
			st.executeUpdate("UPDATE userlist SET roles = " + roles +" WHERE userID = " + userId);

			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
}
