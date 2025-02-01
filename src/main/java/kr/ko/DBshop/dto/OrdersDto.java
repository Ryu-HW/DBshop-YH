package kr.ko.DBshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersDto {

    private Integer orderId;
    private int userId;
    private String orderStatus;
    private Long totalAmount;
    private String shippingAddress;
    private String createdAt;

    public OrdersDto(int userId, String orderStatus, Long totalAmount, String shippingAddress) {
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
    }
}
