package kr.ko.DBshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemProductDto {

    private int productId;
    private String productName;
    private double price;
    private int quantity;
}
