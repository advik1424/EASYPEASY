package com.easypeasy.EPS_BACKEND.SHOP.contoller;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.Shoprequestdto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.Shopresponsedto;
import com.easypeasy.EPS_BACKEND.SHOP.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public Shopresponsedto createShop(
            @Valid @RequestBody Shoprequestdto shoprequestdto) {

        return shopService.createshop(shoprequestdto);
    }
@GetMapping("/{id}")
    public Shopresponsedto getshopbyid(@PathVariable long id){


        Shopresponsedto response=shopService.getShopById(id);

        return response;

    }

    @PutMapping("/{id}")
    public Shopresponsedto updateShop(
            @PathVariable Long id,
            @Valid @RequestBody Shoprequestdto shoprequestdto) {

        return shopService.updateShop(id, shoprequestdto);
    }

    @DeleteMapping("/{id}")
    public String deleteShop(@PathVariable Long id) {

        shopService.deleteShop(id);

        return "Shop deleted successfully";
    }
}