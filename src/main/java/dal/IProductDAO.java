package dal;
import dto.IProductDTO;


import java.util.List;

public interface IProductDAO {

    void createProduct(IProductDTO product) throws DALException;

    IProductDTO getProductId(int productId) throws DALException;
    List<IProductDTO> getProductList() throws DALException;

    void updateProduct(IProductDTO product) throws DALException;

    public class DALException extends Exception {
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }

}