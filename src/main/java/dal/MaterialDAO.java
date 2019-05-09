package dal;

import dto.IMaterialDTO;
import dto.MaterialDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO implements IMaterialDAO {

	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s160068?"
				+ "user=s160068&password=D8meeg0vOUC5OjertVLZV");
	}

	@Override
	public void createMaterial(IMaterialDTO material) throws DALException {

		try(Connection c = createConnection()){
			Statement statement = c.createStatement();
			PreparedStatement st = c.prepareStatement("INSERT INTO Råvare_Batches VALUES (?,?,?,?,?,?)");

			ResultSet rs = statement.executeQuery("SELECT * FROM Råvare_Batches WHERE råvare_batch_id = " + material.getMaterialBatchId());
			if (rs.next()){
				throw new DALException("ID already in use");
			}
			rs = statement.executeQuery("SELECT * FROM Produktionsledere WHERE bruger_id = " + material.getUserId());
			if(!rs.next()){
				throw new DALException("User does not exist, or does not have permission to order");
			}
			rs = statement.executeQuery("SELECT * FROM Ingredienser WHERE ingrediens_id = " + material.getIngredientId());
			if(!rs.next()){
				throw new DALException("Ingredient does not exist");
			}

			st.setInt(1, material.getMaterialBatchId());
			st.setInt(2, material.getIngredientId());
			st.setInt(3, material.getUserId());
			st.setDate(4, material.getDate());
			st.setBoolean(5, material.getOrder());
			st.setDouble(6, material.getAmount());
			st.executeUpdate();


		}
		catch (SQLException e) {
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public IMaterialDTO getMaterial(int materialId) throws DALException {
		IMaterialDTO material = new MaterialDTO();
		try (Connection c = createConnection()){

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Råvare_Batches WHERE råvare_batch_id = " + materialId);
			rs.next();

			material.setMaterialBatchId(rs.getInt("råvare_batch_id"));
			material.setIngredientId(rs.getInt("ingrediens_id"));
			material.setUserId(rs.getInt("bruger_id"));
			material.setAmount(rs.getDouble("mængde"));
			material.setDate(rs.getDate("dato"));
			material.setOrder(rs.getBoolean("bestil"));

		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return material;

	}

	@Override
	public List<IMaterialDTO> getMaterialList() throws DALException {
		IMaterialDTO material = new MaterialDTO();
		List<IMaterialDTO> materialList = new ArrayList<>();

		try (Connection c = createConnection()){

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Råvare_Batches");

			while (rs.next())
			{
				material.setMaterialBatchId(rs.getInt("råvare_batch_id"));
				material.setIngredientId(rs.getInt("ingrediens_id"));
				material.setUserId(rs.getInt("bruger_id"));
				material.setAmount(rs.getDouble("mængde"));
				material.setDate(rs.getDate("dato"));
				material.setOrder(rs.getBoolean("bestil"));

				materialList.add(material);
			}

		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}

		return materialList;
	}

	@Override
	public void updateMaterial(IMaterialDTO material) throws DALException {
		try (Connection c = createConnection()){

			PreparedStatement st = c.prepareStatement("UPDATE Råvare_Batches SET mængde = ? WHERE råvare_batch_id = ?");

			st.setDouble(1,material.getAmount());
			st.setInt(2,material.getMaterialBatchId());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
}
