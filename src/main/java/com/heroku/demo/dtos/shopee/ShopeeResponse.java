package com.heroku.demo.dtos.shopee;

import lombok.Data;

@Data
public class ShopeeResponse {
    private ProductShopeeItem data;
    private Integer error;
    private String error_msg;
}
