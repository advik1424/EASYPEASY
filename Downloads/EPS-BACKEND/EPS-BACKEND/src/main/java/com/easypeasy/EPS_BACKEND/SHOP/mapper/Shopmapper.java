package com.easypeasy.EPS_BACKEND.SHOP.mapper;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.Shoprequestdto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.Shopresponsedto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Shop;
import org.springframework.stereotype.Component;

@Component
public class Shopmapper {


    public Shop toentity(Shoprequestdto shoprequestdto){


        Shop shop=new Shop();

        shop.setShopName(shoprequestdto.getShopName());
        shop.setAddress(shoprequestdto.getAddress());

        return shop;

    }




    public Shopresponsedto toresponse(Shop shop){


        Shopresponsedto s1 =new Shopresponsedto();

        s1.setId(shop.getId());

        s1.setShopName(shop.getShopName());
        s1.setAddress(shop.getAddress());

        return s1;



    }
}
