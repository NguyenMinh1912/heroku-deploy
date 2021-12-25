package com.heroku.demo.dtos.shopee;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductShopeeItem {
   private Long productId;
   private Long shopid;
   private Long itemid;
   private Double price_max_before_discount;
//   private Double has_lowest_price_guarantee;
   private Double price_before_discount;
   private Double price_min_before_discount;
   private Double price_min;
   private Double price_max;
   private Double price;
   private Long stock;
   private String discount;
   private Long historical_sold;
   private Long sold;
   private Integer show_discount;
   private Integer raw_discount;
   private String name;
   private Long ctime;
   private String item_status;
   private Long modelId;
   private List<Model> models = new ArrayList<>();


   public Double getPrice() {
      return price / 100000;
   }
}


