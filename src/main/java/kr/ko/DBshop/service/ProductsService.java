package kr.ko.DBshop.service;

import kr.ko.DBshop.dto.ProductsDto;
import kr.ko.DBshop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    ProductMapper productMapper;

    public List<ProductsDto> getAllproducts(){
        return productMapper.selectAllProducts();
    }
    public List<ProductsDto> getAllProductsByCategoryId(int categoryId){
        return productMapper.selectAllProductsByCategoryId(categoryId);
    };
}
