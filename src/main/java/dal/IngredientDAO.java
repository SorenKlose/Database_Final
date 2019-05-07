package dal;

import dto.IIngredientDTO;
import dto.IngredientDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IngredientDAO implements IIngredientDAO {
	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s160068?"
				+ "user=s160068&password=D8meeg0vOUC5OjertVLZV");
	}


	@Override
	public void createIngredient(IIngredientDTO ingredient) throws IIngredientDAO.DALException {

		try {
			Connection c = createConnection();
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery("SELECT ingrediens_id FROM Ingredienser");
			LinkedList<Integer> uid = new LinkedList<>();
			boolean idUsed = false;

			while (rs.next()){
				uid.add(rs.getInt("ingrediens_id"));
			}

			PreparedStatement st = c.prepareStatement("INSERT INTO Ingredienser VALUES (?,?,?,?)");
			PreparedStatement ps;
			int ingredientId = ingredient.getIngredientId();
			String ingredientName = ingredient.getIngredientName();
			boolean active = ingredient.getActive();
			double margin = ingredient.getMargin();

			for (int i = 0; i < uid.size(); i++){
				if (ingredientId == uid.get(i)){
					System.out.println("ingredientID already in use");
					idUsed = true;
					break;
				}
			}

			if (idUsed == false) {
				st.setInt(1, ingredientId);
				st.setString(2, ingredientName);
				st.setBoolean(3, active);
				st.setDouble(4, margin);
				st.executeUpdate();
			}

			c.close();
		} catch (SQLException e) {
			throw new IIngredientDAO.DALException(e.getMessage());
		}
	}


	@Override
	public IIngredientDTO getIngredient(int ingredientId) throws IIngredientDAO.DALException {

		IIngredientDTO ingrediens = new IngredientDTO();


		try {
			Connection c = createConnection();

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Ingredienser WHERE ingrediens_id = " + ingredientId);
			rs.next();

			ingrediens.setIngredientId(rs.getInt("ingrediens_id"));
			ingrediens.setIngredientName(rs.getString("ingrediens_navn"));
			ingrediens.setMargin(rs.getDouble("afvigelse"));
			ingrediens.setActive(rs.getBoolean("isAktiv"));

			c.close();
		} catch (SQLException e) {
			throw new IIngredientDAO.DALException(e.getMessage());
		}
		return ingrediens;
	}


	@Override
	public List<IIngredientDTO> getIngredientList() throws IIngredientDAO.DALException {

		IIngredientDTO user = new IngredientDTO();
		List<IIngredientDTO> ingredientList = new ArrayList<>();

		try {
			Connection c = createConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Ingredienser");

			while (rs.next())
			{
				user.setIngredientId(rs.getInt("ingrediens_id"));
				user.setIngredientName(rs.getString("ingrediens_navn"));
				user.setMargin(rs.getDouble("afvigelse"));

				ingredientList.add(user);
			}

			c.close();
		} catch (SQLException e) {
			throw new IIngredientDAO.DALException(e.getMessage());
		}
		return ingredientList;
	}


	@Override
	public void updateIngredient(IIngredientDTO ingredient) throws IIngredientDAO.DALException {

		try {
			Connection c = createConnection();
			PreparedStatement st = c.prepareStatement("UPDATE ingredienser SET ingrediens_navn = ?, afvigelse = ?, isAktiv = ? WHERE userID = ?");
			int ingredientId = ingredient.getIngredientId();
			String ingredientName = ingredient.getIngredientName();
			boolean active = ingredient.getActive();
			double margin = ingredient.getMargin();

			st.setString(1,ingredientName);
			st.setDouble(2,margin);
			st.setBoolean(3, active);
			st.setInt(4,ingredientId);
			st.executeUpdate();

			c.close();
		} catch (SQLException e) {
			throw new IIngredientDAO.DALException(e.getMessage());
		}
	}
}
