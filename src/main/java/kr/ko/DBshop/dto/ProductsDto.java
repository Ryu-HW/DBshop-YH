package kr.ko.DBshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductsDto {

    private Integer productId;
    private Integer categoryId;
    private String productName;
    private BigDecimal price;
    private int stockQuantity;
    private String description;
    private String createdAt;

}
