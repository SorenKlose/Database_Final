package dal;

import dto.IProductDTO;
import dto.ProductDTO;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProductDAO implements IProductDAO{
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185086?"
                + "user=s185086&password=PL9404AoCEaBAFfUjd9dG");
    }
    @Override
    public void createProduct (IProductDTO product) throws DALException   {
        try{
            Connection c = createConnection();
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT produkt_id FROM Produkter");
            LinkedList<Integer> uid = new LinkedList<>();
            boolean idUsed = false;

            while (rs.next()) {
                uid.add(rs.getInt("produkt_id"));
            }
            PreparedStatement st = c.prepareStatement("INSERT INTO Produkter VALUES (?,?)");
            PreparedStatement ps;
            int productId = product.getProductId();
            String productName = product.getProductName();

            for (int i = 0; i < uid.size(); i++){
                if (productId == uid.get(i)){
                    System.out.println("Product ID in use");
                    idUsed = true;
                    break;
                }
            }
            if (idUsed == false) {
              st.setInt(1,productId);
              st.setString(2, productName);
              st.executeUpdate();
              }
              c.close();
        } catch (SQLException e) {
            throw new IProductDAO.DALException(e.getMessage());
        }
    }
    @Override
    public IProductDTO getProduct(int productId) throws DALException {

        IProductDTO product = new ProductDTO();

        try {
            Connection c = createConnection();

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Produkter WHERE produkt_id = " + productId);
            rs.next();

            product.setProductId(rs.getInt("produkt_id"));
            product.setProductName(rs.getString("produkt_navn"));

            rs = st.executeQuery("SELECT produkt_navn FROM Produket WHERE produkt_id = " + productId);
            while(rs.next()){
                user.getRoles().add(rs.getString(1));
            }

            c.close();
        } catch (SQLException e) {
            throw new IProductDAO.DALException(e.getMessage());
        }
        return product;
    }
}
