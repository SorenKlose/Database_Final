package dal;

import dto.IRecipeDTO;
import dto.RecipeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecipeDAO implements IRecipeDAO{
	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s160068?"
				+ "user=s160068&password=D8meeg0vOUC5OjertVLZV");
	}


	@Override
	public void createRecipe(IRecipeDTO recipe) throws IRecipeDAO.DALException {

		try {
			Connection c = createConnection();
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery("SELECT opskrift_id FROM Opskrifter");
			LinkedList<Integer> uid = new LinkedList<>();
			boolean idUsed = false;

			while (rs.next()){
				uid.add(rs.getInt("opskrift_id"));
			}

			PreparedStatement st = c.prepareStatement("INSERT INTO Opskrifter VALUES (?,?,?)");
			int recipeId = recipe.getRecipeId();
			int productId = recipe.getProductId();
			String date = recipe.getDate();

			for (int i = 0; i < uid.size(); i++){
				if (recipeId == uid.get(i)){
					System.out.println("recipeID already in use");
					idUsed = true;
					break;
				}
			}

			if (idUsed == false) {
				st.setInt(1, recipeId);
				st.setInt(2, productId);
				st.setString(3, date);
				st.executeUpdate();
			}

			c.close();
		} catch (SQLException e) {
			throw new IRecipeDAO.DALException(e.getMessage());
		}
	}


	@Override
	public IRecipeDTO getRecipe(int recipeId) throws IRecipeDAO.DALException {

		IRecipeDTO recipe = new RecipeDTO();


		try {
			Connection c = createConnection();

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Opskrifter WHERE opskrift_id = " + recipeId);
			rs.next();

			recipe.setRecipeId(rs.getInt("opskrift_id"));
			recipe.setProductId(rs.getInt("produkt_id"));
			recipe.setDate(rs.getString("dato"));

			c.close();
		} catch (SQLException e) {
			throw new IRecipeDAO.DALException(e.getMessage());
		}
		return recipe;
	}


	@Override
	public List<IRecipeDTO> getRecipeList() throws IRecipeDAO.DALException {

		IRecipeDTO recipe = new RecipeDTO();
		List<IRecipeDTO> recipeList = new ArrayList<>();

		try {
			Connection c = createConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Ingredienser");

			while (rs.next())
			{
				recipe.setRecipeId(rs.getInt("opskrift_id"));
				recipe.setProductId(rs.getInt("produkt_id"));
				recipe.setDate(rs.getString("dato"));

				recipeList.add(recipe);
			}

			c.close();
		} catch (SQLException e) {
			throw new IRecipeDAO.DALException(e.getMessage());
		}
		return recipeList;
	}


	@Override
	public void updateRecipe(IRecipeDTO recipe) throws IRecipeDAO.DALException {

		try {
			Connection c = createConnection();
			PreparedStatement st = c.prepareStatement("UPDATE opskrifter SET produkt_id = ?, dato = ? WHERE opskrift_id = ?");
			int recipeId = recipe.getRecipeId();
			int productId = recipe.getProductId();
			int userId = recipe.getUserId();
			String date = recipe.getDate();

			st.setInt(1,productId);
			st.setString(2, date);
			st.setInt(3,recipeId);
			st.executeUpdate();

			c.close();
		} catch (SQLException e) {
			throw new IRecipeDAO.DALException(e.getMessage());
		}
	}
}
