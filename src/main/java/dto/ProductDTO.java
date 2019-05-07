package dto;

import java.io.Serializable;
import java.util.List;

public class ProductDTO implements Serializable, IProductDTO {
    private int productId;
    private String productName;
    private List<String> productList;

    @Override
    public List<String> getProductList() {
        return productList;
    }

    @Override
    public int getProductId() {
        return productId;
    }
    @Override
    public void setProductId(int productId) {
        this.productId = productId;
    }
    @Override
    public String getProductName() {
        return productName;
    }
    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }
}

