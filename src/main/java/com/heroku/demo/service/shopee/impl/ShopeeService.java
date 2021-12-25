package com.heroku.demo.service.shopee.impl;

import com.heroku.demo.constant.ShopeeURL;
import com.heroku.demo.dtos.shopee.APIResponse;
import com.heroku.demo.dtos.shopee.ProductShopeeItem;

import com.heroku.demo.dtos.shopee.ShopeeResponse;
import com.heroku.demo.dtos.shopee.ShopeeShopDTO;
import com.heroku.demo.service.shopee.IShopeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.heroku.demo.constant.ShopeeURL.*;


@Service
public class ShopeeService implements IShopeeService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public APIResponse<ProductShopeeItem> getShopeeItemByItemIdAndShopId(Long itemId, Long shopId) {
        String url = UriComponentsBuilder.fromHttpUrl(GET_ITEM_V4)
                .queryParam("itemId", "{itemId}")
                .queryParam("shopId", "{shopId}")
                .encode()
                .toUriString();

        Map<String, Object> params = new HashMap<>();
        params.put("itemId", itemId);
        params.put("shopId", shopId);
        ResponseEntity<ShopeeResponse> response = restTemplate.getForEntity(url, ShopeeResponse.class, params);
        if (response.getStatusCode().value() == 200){
            ShopeeResponse body = response.getBody();
            Integer err = body.getError();
            if (err != null){
                return APIResponse.of(null, err, body.getError_msg());
            }
            try {
//                ProductShopeeItem productShopeeItem = modelMapper.map(response.getBody().getData(), ProductShopeeItem.class);

                return APIResponse.of(response.getBody().getData(), 200);
            }catch (Exception e){
                e.printStackTrace();
                return APIResponse.of(null, 405, "Mapper has some issue");
            }

        }
        return APIResponse.of(null, 200);
    }

    @Override
    public APIResponse<ShopeeShopDTO> getShopInfoById(Long shopId) {
        String url = UriComponentsBuilder.fromHttpUrl(GET_SHOP_INFO_V4)
                .queryParam("shopid", "{shopId}")
                .encode()
                .toUriString();
        Map<String, Object> params = new HashMap<>();
        params.put("shopId", shopId);
        ResponseEntity<ShopeeResponse> response = restTemplate.getForEntity(url, ShopeeResponse.class, params);
        Integer resStatus = response.getStatusCode().value();
        if (resStatus == 200){
            ShopeeResponse shopeeResponse = response.getBody();
            Integer shopeeResponseErrCode = shopeeResponse.getError();
            if (shopeeResponseErrCode != null) return APIResponse.of(null, shopeeResponseErrCode, shopeeResponse.getError_msg());
//            ShopeeShopDTO shop = modelMapper.map(shopeeResponse.getData(), ShopeeShopDTO.class);
//            return APIResponse.of(shop, 200);
        }
        return null;
    }

    @Override
    public ResponseEntity<Object> getByKeyword(String keyword, Integer page, Integer limit) {
        String url = UriComponentsBuilder.fromHttpUrl(SEARCH_ITEMS)
                .queryParam("by", "{by}")
                .queryParam("keyword", "{keyword}")
                .queryParam("limit", "{limit}")
                .queryParam("newest", "{newest}")
                .queryParam("order", "{order}")
                .queryParam("page_type", "{page_type}")
                .queryParam("scenario", "{scenario}")
                .queryParam("version", "{version}")
                .queryParam("match_id", "{match_id}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("by", "relevancy");
        params.put("keyword", keyword);
        params.put("limit", limit + "");
        params.put("newest", (page * limit) +"");
        params.put("order", "desc");
        params.put("page_type", "search");
        params.put("scenario", "PAGE_SUB_CATEGORY_SEARCH");
        params.put("version", "2");
        params.put("match_id", "11108503");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("cookie", "REC_T_ID=bf78b5c8-5c18-11ec-87c5-b47af14b38fc; SPC_F=BwKDY48Gkb2gGnQGqKHsJuG9tXgy1mJv; SPC_SI=bfftocvn.egTahAhB2SApJmt9zJAWFK2xkJvxYAu1; _gcl_au=1.1.224278152.1639402191; SPC_IA=-1; SPC_EC=-; SPC_U=-; _fbp=fb.1.1639402190802.1936488770; _gid=GA1.2.1558117312.1639402191; csrftoken=sxKdJtQ5eo3ErLsDTwY6hGbXfd1AmaLQ; _hjSessionUser_868286=eyJpZCI6IjM1MDU2YzJhLTEwOWEtNWE1Ni05MzAwLTQ3ZDhiNDM0MzJlNyIsImNyZWF0ZWQiOjE2Mzk0MDI5Nzg2OTAsImV4aXN0aW5nIjp0cnVlfQ==; _gcl_aw=GCL.1639451181.EAIaIQobChMInaqIyafi9AIVTKqWCh09NA9gEAAYASAAEgIMiPD_BwE; _gac_UA-61914164-6=1.1639451182.EAIaIQobChMInaqIyafi9AIVTKqWCh09NA9gEAAYASAAEgIMiPD_BwE; _med=refer; SPC_T_ID=Ulwz0WL2J02kTpImNKeotzm6wrIMCko62wVYXzh79fSKWDlaqFHeeT3vjupavsZI9yWC5JSnvFf5ufrT4keuyzlGxMTu67DiaNlPdQjoCSk=; SPC_T_IV=DKt6FrFnvsitwLYo/bRKoQ==; cto_bundle=Ty__d18yemVlNVJOUCUyQlA4V0R2YjE0RHFpU3JYbkZRaWNIU2ZhWHY5bWw2JTJCNmZFZXp5UjJoTkdhTnZXJTJCWDBDWVhqYm5WY2NmRU5nd3dJaE43SDEzRFdsdE5vMWtqJTJCMzM4R3NMQ3k5R2Y5REp2Q0NGMEw0UjklMkJnUmtpaVZzZUJ6QVRJazV3TGVZZ3JnTFpLanNoNUJkNzV1QXp3JTNEJTNE; _hjSession_868286=eyJpZCI6IjA3YWZmMGE0LTY1Y2MtNDllMi1iZTJhLWM0N2U4MmJjMmZhZSIsImNyZWF0ZWQiOjE2Mzk0ODQ0NjAwNzR9; _hjAbsoluteSessionInProgress=0; AMP_TOKEN=%24NOT_FOUND; _ga=GA1.2.738938536.1639402191; _dc_gtm_UA-61914164-6=1; _ga_M32T05RVZT=GS1.1.1639484459.7.1.1639484463.56; SPC_R_T_ID=\"jaSr59TRN6adl8cQV+pU60e4zjilCpb8rHUheXm9oDVopDi9jD4HTn6jBVXuqFA80IbvanXnJ0fG1NEGbGp+MOH94zBv9mrOvj/vabqWyLY=\"; SPC_T_IV=\"9J/dO3m0boPQamtOatO4XQ==\"; SPC_R_T_IV=\"9J/dO3m0boPQamtOatO4XQ==\"; SPC_T_ID=\"jaSr59TRN6adl8cQV+pU60e4zjilCpb8rHUheXm9oDVopDi9jD4HTn6jBVXuqFA80IbvanXnJ0fG1NEGbGp+MOH94zBv9mrOvj/vabqWyLY=\"");
        headers.add("if-none-match", "55b03-18b85166f60985ef0d27d4/425ada389f");

        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        headers.add("x-requested-with", "XMLHttpRequest");
        headers.add("x-shopee-language", "vi");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        try {
//            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class, params);
            ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class, params);
            return exchange;
        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.status(500).body(new Object());
        }

    }

    @Override
    public ResponseEntity<Object> searchItems(String path, Integer page, Integer limit) {
//        String url = SEARCH_ITEMS + path;
        Map<String, String> params = new HashMap<>();
        params.put("limit", limit + "");
        params.put("newest", (page * limit) +"");
        HttpHeaders headers = new HttpHeaders();

        String url = UriComponentsBuilder.fromHttpUrl(SEARCH_ITEMS)
//                .queryParam("by", "{by}")
//                .queryParam("keyword", "{keyword}")
                .queryParam("limit", "{limit}")
                .queryParam("newest", "{newest}")
//                .queryParam("order", "{order}")
//                .queryParam("page_type", "{page_type}")
//                .queryParam("scenario", "{scenario}")
//                .queryParam("version", "{version}")
//                .queryParam("match_id", "{match_id}")
                .encode()

                .toUriString();



        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("cookie", "REC_T_ID=bf78b5c8-5c18-11ec-87c5-b47af14b38fc; SPC_F=BwKDY48Gkb2gGnQGqKHsJuG9tXgy1mJv; SPC_SI=bfftocvn.egTahAhB2SApJmt9zJAWFK2xkJvxYAu1; _gcl_au=1.1.224278152.1639402191; SPC_IA=-1; SPC_EC=-; SPC_U=-; _fbp=fb.1.1639402190802.1936488770; _gid=GA1.2.1558117312.1639402191; csrftoken=sxKdJtQ5eo3ErLsDTwY6hGbXfd1AmaLQ; _hjSessionUser_868286=eyJpZCI6IjM1MDU2YzJhLTEwOWEtNWE1Ni05MzAwLTQ3ZDhiNDM0MzJlNyIsImNyZWF0ZWQiOjE2Mzk0MDI5Nzg2OTAsImV4aXN0aW5nIjp0cnVlfQ==; _gcl_aw=GCL.1639451181.EAIaIQobChMInaqIyafi9AIVTKqWCh09NA9gEAAYASAAEgIMiPD_BwE; _gac_UA-61914164-6=1.1639451182.EAIaIQobChMInaqIyafi9AIVTKqWCh09NA9gEAAYASAAEgIMiPD_BwE; _med=refer; SPC_T_ID=Ulwz0WL2J02kTpImNKeotzm6wrIMCko62wVYXzh79fSKWDlaqFHeeT3vjupavsZI9yWC5JSnvFf5ufrT4keuyzlGxMTu67DiaNlPdQjoCSk=; SPC_T_IV=DKt6FrFnvsitwLYo/bRKoQ==; cto_bundle=Ty__d18yemVlNVJOUCUyQlA4V0R2YjE0RHFpU3JYbkZRaWNIU2ZhWHY5bWw2JTJCNmZFZXp5UjJoTkdhTnZXJTJCWDBDWVhqYm5WY2NmRU5nd3dJaE43SDEzRFdsdE5vMWtqJTJCMzM4R3NMQ3k5R2Y5REp2Q0NGMEw0UjklMkJnUmtpaVZzZUJ6QVRJazV3TGVZZ3JnTFpLanNoNUJkNzV1QXp3JTNEJTNE; _hjSession_868286=eyJpZCI6IjA3YWZmMGE0LTY1Y2MtNDllMi1iZTJhLWM0N2U4MmJjMmZhZSIsImNyZWF0ZWQiOjE2Mzk0ODQ0NjAwNzR9; _hjAbsoluteSessionInProgress=0; AMP_TOKEN=%24NOT_FOUND; _ga=GA1.2.738938536.1639402191; _dc_gtm_UA-61914164-6=1; _ga_M32T05RVZT=GS1.1.1639484459.7.1.1639484463.56; SPC_R_T_ID=\"jaSr59TRN6adl8cQV+pU60e4zjilCpb8rHUheXm9oDVopDi9jD4HTn6jBVXuqFA80IbvanXnJ0fG1NEGbGp+MOH94zBv9mrOvj/vabqWyLY=\"; SPC_T_IV=\"9J/dO3m0boPQamtOatO4XQ==\"; SPC_R_T_IV=\"9J/dO3m0boPQamtOatO4XQ==\"; SPC_T_ID=\"jaSr59TRN6adl8cQV+pU60e4zjilCpb8rHUheXm9oDVopDi9jD4HTn6jBVXuqFA80IbvanXnJ0fG1NEGbGp+MOH94zBv9mrOvj/vabqWyLY=\"");
        headers.add("if-none-match", "55b03-18b85166f60985ef0d27d4/425ada389f");

        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        headers.add("x-requested-with", "XMLHttpRequest");
        headers.add("x-shopee-language", "vi");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        try {
            ResponseEntity<Object> exchange = restTemplate.exchange(url + "&" + path, HttpMethod.GET, entity, Object.class, params);
            return exchange;
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Object());
        }

    }

    @Override
    public ResponseEntity<Object> getItemInfo(Long itemId, Long shopId) {
        String url = UriComponentsBuilder.fromHttpUrl(GET_ITEM_V4)
                .queryParam("itemId", "{itemId}")
                .queryParam("shopId", "{shopId}")
                .encode()
                .toUriString();

        Map<String, Object> params = new HashMap<>();
        params.put("itemId", itemId);
        params.put("shopId", shopId);
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class, params);

        return ResponseEntity.ok(response.getBody());
    }

    @Override
    public ResponseEntity<Object> getShopInfo(Long shopId) {
        String url = UriComponentsBuilder.fromHttpUrl(GET_SHOP_INFO_V4)
                .queryParam("shopid", "{shopId}")
                .encode()
                .toUriString();
        Map<String, Object> params = new HashMap<>();
        params.put("shopId", shopId);
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class, params);
        return ResponseEntity.ok(response.getBody());
    }


}
