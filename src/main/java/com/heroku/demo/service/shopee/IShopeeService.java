package com.heroku.demo.service.shopee;

import com.heroku.demo.dtos.shopee.APIResponse;
import com.heroku.demo.dtos.shopee.ProductShopeeItem;
import com.heroku.demo.dtos.shopee.ShopeeShopDTO;
import org.springframework.http.ResponseEntity;

public interface IShopeeService {

    APIResponse<ProductShopeeItem> getShopeeItemByItemIdAndShopId(Long itemId, Long shopId);

    APIResponse<ShopeeShopDTO> getShopInfoById(Long shopId);

    ResponseEntity<Object> getByKeyword(String keyword, Integer page, Integer limit);

    ResponseEntity<Object> searchItems(String path, Integer page, Integer limit);

    ResponseEntity<Object> getItemInfo(Long itemId, Long shopId);

    ResponseEntity<Object> getShopInfo(Long shopId);

}
