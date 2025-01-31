package kr.ko.DBshop.service;

import kr.ko.DBshop.dto.CartItemProductDto;
import kr.ko.DBshop.dto.CartItemsDto;
import kr.ko.DBshop.mapper.CartItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemsService {

    @Autowired
    CartItemsMapper cartItemsMapper;

    public void addCartItemByUserIdAndProductId(int userId,int productId){
        CartItemsDto cartItemsDto = cartItemsMapper.selectCartItemsByUserIdAndProductId(userId, productId);
        if (cartItemsDto != null) {
            int quantity = cartItemsDto.getQuantity()+1;
            cartItemsMapper.updateCartItemQuantity(userId, productId,quantity);
        }else {
            cartItemsMapper.insertCartItem(userId, productId);
        }


    }
    public List<CartItemProductDto> getCartItemsWithProductByUserId(int userId){
        return cartItemsMapper.selectCartItemsWithProductByUserId(userId);
    }
}
