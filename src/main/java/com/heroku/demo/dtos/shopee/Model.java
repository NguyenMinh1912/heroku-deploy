package com.heroku.demo.dtos.shopee;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Model {
    private Long itemid;
    private Integer status;
    private Long current_promotion_reserved_stock;
    private String name;
    private Long promotionid;
    private Double price;
    private Double price_before_discount;
    private Long modelid;
    private Long stock;
}
