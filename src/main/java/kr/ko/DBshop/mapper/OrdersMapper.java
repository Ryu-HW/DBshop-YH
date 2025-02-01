package kr.ko.DBshop.mapper;

import kr.ko.DBshop.dto.OrdersDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {

    public void insertOrder(OrdersDto ordersDto);
}
