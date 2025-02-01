package kr.ko.DBshop.controller;

import kr.ko.DBshop.dto.UsersDto;
import kr.ko.DBshop.service.CartItemsService;
import kr.ko.DBshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersApiController {

    @Autowired
    UsersService usersService;

    @Autowired
    CartItemsService cartItemsService;

    @PatchMapping("/cart/update/{productId}")
    public ResponseEntity<Integer> updateCartItemQuantity(@PathVariable("productId") int productId,
                                                 @RequestBody int quantity){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UsersDto userDto = usersService.getUserByEmail(email);


        if(quantity < 1){
            cartItemsService.deleteCartItemByUserIdAndProductId(userDto.getUserId(),productId);
            return ResponseEntity.ok(quantity);

        }else {

            boolean isUpdated = cartItemsService.updateCartItemQuantityByUserIdAndProductId(userDto.getUserId(),productId,quantity);

            if(isUpdated){
                return ResponseEntity.ok(quantity);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
    }

    @DeleteMapping("/cart/delete/{productId}")
    public void deleteCartItem(@PathVariable("productId") int productId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UsersDto userDto = usersService.getUserByEmail(email);
        cartItemsService.deleteCartItemByUserIdAndProductId(userDto.getUserId(),productId);
    }



}
