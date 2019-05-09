package dal;

import dto.IMaterialDTO;
import dto.IProdBatchDTO;

import java.sql.*;

public class ProdBatchDAO implements IProdBatchDAO {
	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s160068?"
				+ "user=s160068&password=D8meeg0vOUC5OjertVLZV");
	}


	@Override
	public void createProdBatch(IProdBatchDTO prodBatch) throws DALException {

		try(Connection c = createConnection()){
			Statement statement = c.createStatement();
			PreparedStatement st = c.prepareStatement("INSERT INTO Produkt_Batches VALUES (?,?,?,?)");

			ResultSet rs = statement.executeQuery("SELECT * FROM Produkt_Batches WHERE produkt_batch_id = " + prodBatch.getProdBatchId());
			if (rs.next()){
				throw new DALException("ID already in use");
			}
			rs = statement.executeQuery("SELECT * FROM Produktionsledere WHERE bruger_id = " + prodBatch.getUserId());
			if(!rs.next()){
				throw new DALException("User does not exist, or does not have permission to order");
			}
			rs = statement.executeQuery("SELECT * FROM Opskrifter WHERE opskrift_id = " + prodBatch.getRecipeId());
			if(!rs.next()){
				throw new DALException("Recipe does not exist");
			}
			Statement MatCheck = c.createStatement();
			ResultSet matCheck;
			for(int check: prodBatch.getMatList()){
				matCheck = MatCheck.executeQuery("SELECT råvare_batch_id FROM Råvare_Batches WHERE råvare_batch_id = " + check);
				if(!matCheck.next()){
					throw new DALException("One of the Pharmaceuts does not exist, or is not a Pharmaceut");
				}
			}
			Statement LabCheck = c.createStatement();
			ResultSet labCheck;
			for(int check: prodBatch.getLabList()){
				labCheck = LabCheck.executeQuery("SELECT bruger_id FROM Laboranter WHERE bruger_id = " + check);
				if(!labCheck.next()){
					throw new DALException("One of the Lab-Techs does not exist, or is not a Lab-Tech");
				}
			}

			st.setInt(1, prodBatch.getProdBatchId());
			st.setInt(2, prodBatch.getRecipeId());
			st.setInt(3, prodBatch.getUserId());
			st.setDate(4, prodBatch.getDate());
			st.executeUpdate();

			PreparedStatement ps;
			for(int labTech: prodBatch.getLabList()){
				ps = c.prepareStatement("INSERT INTO Laboranter_Produkt_Batches VALUES (?,?)");
				ps.setInt(1, labTech);
				ps.setInt(2, prodBatch.getProdBatchId());
				ps.executeUpdate();
			}

			PreparedStatement pre;
			for(int mat: prodBatch.getMatList()){
				pre = c.prepareStatement("INSERT INTO Produkt_Batches_Råvare_Batches VALUES (?,?)");
				pre.setInt(1, prodBatch.getProdBatchId());
				pre.setInt(2, mat);
				pre.executeUpdate();
			}

		}
		catch (SQLException e) {
			throw new DALException(e.getMessage());
		}

	}
}
