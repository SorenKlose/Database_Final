package dto;

import java.util.List;

public interface IProductDTO {
    int getProductId();

    void setProductId(int productId);

    String getProductName();

    void setProductName(String productName);

    List<String> getProductList();

    void setProductList(List<String> productList);

}
