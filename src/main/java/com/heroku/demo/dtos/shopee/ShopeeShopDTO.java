package com.heroku.demo.dtos.shopee;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShopeeShopDTO {
    private Long shopid;
    private Long userid;
    private Long last_active_time;
    private String place;
    private AccountShopeeDTO account;
    private String shop_location;
    private Integer item_count;
    private Double rating_star;
    private String name;
    private Long ctime;
    private Integer response_time;
    private Long follower_count;
    private Integer rating_bad;
    private Integer rating_good;
    private Integer rating_normal;
}
