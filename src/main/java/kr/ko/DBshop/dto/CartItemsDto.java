package kr.ko.DBshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemsDto {

    private Integer cartItemId;
    private int userId;
    private int productId;
    private int quantity;
    private String createdAt;
}
