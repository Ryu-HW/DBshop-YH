package kr.ko.DBshop.service;

import kr.ko.DBshop.dto.OrderItemsDto;
import kr.ko.DBshop.dto.ProductsDto;
import kr.ko.DBshop.mapper.OrderItemsMapper;
import kr.ko.DBshop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    OrderItemsMapper orderItemsMapper;

    public void createOrderItem(int orderId, List<String> productNames,List<Integer> quantities){

        for(int i=0;i < productNames.size();i++){
            ProductsDto product = productMapper.selectProductByProductName(productNames.get(i));
            OrderItemsDto orderItemsDto = new OrderItemsDto(orderId,product.getProductId(),quantities.get(i),product.getPrice());
            orderItemsMapper.insertOrderItem(orderItemsDto);
        }

    }
}
