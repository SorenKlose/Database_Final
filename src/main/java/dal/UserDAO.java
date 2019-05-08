package dal;

import dto.IUserDTO;
import dto.UserDTO;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements IUserDAO {
	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s160068?"
				+ "user=s160068&password=D8meeg0vOUC5OjertVLZV");
	}


	@Override
	public void createUser(IUserDTO user) throws DALException {

		try {
			Connection c = createConnection();
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery("SELECT bruger_id FROM Brugere");
			LinkedList<Integer> uid = new LinkedList<>();
			boolean idUsed = false;

			while (rs.next()){
				uid.add(rs.getInt("userID"));
			}

			PreparedStatement st = c.prepareStatement("INSERT INTO Brugere VALUES (?,?,?)");
			PreparedStatement ps;
			int userId = user.getUserId();
			String userName = user.getUserName();
			String ini = user.getIni();

			for (int i = 0; i < uid.size(); i++){
				if (userId == uid.get(i)){
					System.out.println("userID already in use");
					idUsed = true;
					break;
				}
			}

			if (idUsed == false) {
				st.setInt(1, userId);
				st.setString(2, userName);
				st.setString(3, ini);
				st.executeUpdate();
				for (String roles : user.getRoles()) {
					ps = c.prepareStatement("INSERT INTO roles VALUES (?,?)");
					ps.setString(1, roles);
					ps.setInt(2, userId);
					ps.executeUpdate();
				}
			}

			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}


	@Override
	public IUserDTO getUser(int userId) throws DALException {

		IUserDTO user = new UserDTO();


		try {
			Connection c = createConnection();

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM userlist WHERE userID = " + userId);
			rs.next();

			user.setUserId(rs.getInt("userID"));
			user.setUserName(rs.getString("userName"));
			user.setIni(rs.getString("ini"));

			rs = st.executeQuery("SELECT roleName FROM roles WHERE userID = " + userId);
			while(rs.next()){
				user.getRoles().add(rs.getString(1));
			}


			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return user;
	}


	@Override
	public List<IUserDTO> getUserList() throws DALException {

		IUserDTO user = new UserDTO();
		List<IUserDTO> userList = new ArrayList<>();

		try {
			Connection c = createConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM userlist");

			while (rs.next())
			{
				user.setUserId(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setIni(rs.getString("ini"));

				userList.add(user);
			}

			for (IUserDTO users: userList) {
				rs = st.executeQuery("SELECT roleName FROM roles WHERE userID = " + user.getUserId());
				while (rs.next()) {
					users.getRoles().add(rs.getString(1));
				}
			}
			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return userList;
	}


	@Override
	public void updateUser(IUserDTO user) throws DALException {

		try {
			Connection c = createConnection();
			PreparedStatement st = c.prepareStatement("UPDATE userlist SET userName = ?, ini = ? WHERE userID = ?");
			PreparedStatement psd = c.prepareStatement("DELETE FROM roles WHERE userID = ?");
			PreparedStatement pst;
			int userId = user.getUserId();
			String userName = user.getUserName();
			String ini = user.getIni();
			List<String> roles = user.getRoles();

			psd.setInt(1,userId);
			psd.executeUpdate();

			st.setString(1,userName);
			st.setString(2,ini);
			st.setInt(3,userId);
			st.executeUpdate();

			for (String roles1 : roles) {
				pst = c.prepareStatement("INSERT INTO roles VALUES (?,?)");
				pst.setString(1, roles1);
				pst.setInt(2, userId);
				pst.executeUpdate();
			}

			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
}