package kr.ko.DBshop.mapper;

import kr.ko.DBshop.dto.ProductsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    public List<ProductsDto> selectAllProducts();
    public ProductsDto selectProductByProductName(String ProductName);
    public List<ProductsDto> selectAllProductsByCategoryId(int categoryId);
    public void updateQuantity(int productId, int stockQuantity);

}
