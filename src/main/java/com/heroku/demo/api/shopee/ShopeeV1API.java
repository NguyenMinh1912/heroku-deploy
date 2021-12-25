package com.heroku.demo.api.shopee;

import com.heroku.demo.service.shopee.IShopeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shopee/v1")
public class ShopeeV1API {

    @Autowired
    private IShopeeService shopeeService;

    @GetMapping("product")
    public ResponseEntity<Object> getShopeeItemByItemIdAndShopId(
            @RequestParam(value = "itemId") Long itemId,
            @RequestParam(value = "shopId") Long shopId
    ) {

        return shopeeService.getItemInfo(itemId, shopId);
    }

    @GetMapping("shop")
    public ResponseEntity<Object> getShopInfoByShopId(
            @RequestParam(value = "shopId") Long shopId
    ) {
        return shopeeService.getShopInfo(shopId);
    }

    @GetMapping("search_items")
    public ResponseEntity<Object> getAllByKeyword(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "path") String path
            ){
        return shopeeService.searchItems(path, page, limit);
    }



}
