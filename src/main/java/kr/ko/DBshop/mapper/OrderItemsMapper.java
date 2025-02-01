package kr.ko.DBshop.mapper;

import kr.ko.DBshop.dto.OrderItemsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemsMapper {

    public void insertOrderItem(OrderItemsDto orderItemsDto);
}
