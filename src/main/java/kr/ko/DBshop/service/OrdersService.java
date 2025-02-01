package kr.ko.DBshop.service;

import kr.ko.DBshop.dto.OrdersDto;
import kr.ko.DBshop.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    public void createOrder(OrdersDto ordersDto){

        ordersMapper.insertOrder(ordersDto);

    }
}
