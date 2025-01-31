package kr.ko.DBshop.mapper;

import kr.ko.DBshop.dto.CartItemProductDto;
import kr.ko.DBshop.dto.CartItemsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartItemsMapper {

    public CartItemsDto selectCartItemsByUserIdAndProductId(int userId,int productId);
    public void updateCartItemQuantity(int userId,int productId,int quantity);
    public void insertCartItem(int userId,int productId);
    public List<CartItemProductDto> selectCartItemsWithProductByUserId(int userId);
}
