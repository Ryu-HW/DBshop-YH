package kr.ko.DBshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemsDto {

    private Integer orderItemId;
    private int orderId;
    private int productId;
    private int quantity;
    private long price;

    public OrderItemsDto(int orderId, int productId, int quantity, long price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price*quantity;
    }
}
