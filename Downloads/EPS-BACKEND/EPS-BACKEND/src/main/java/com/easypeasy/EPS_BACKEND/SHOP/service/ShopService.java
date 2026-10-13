package com.easypeasy.EPS_BACKEND.SHOP.service;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.Shoprequestdto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.Shopresponsedto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Shop;
import com.easypeasy.EPS_BACKEND.SHOP.mapper.Shopmapper;
import com.easypeasy.EPS_BACKEND.SHOP.repository.ShopRepository;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    private final Shopmapper shopmapper;

    public ShopService(
            ShopRepository shopRepository,
            Shopmapper shopmapper) {

        this.shopRepository = shopRepository;
        this.shopmapper = shopmapper;
    }

    public Shopresponsedto createshop(
            Shoprequestdto shoprequestdto) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user = (User) authentication.getPrincipal();

        Shop shop = shopmapper.toentity(shoprequestdto);

        shop.setOwner(user);

        Shop savedShop = shopRepository.save(shop);

        return shopmapper.toresponse(savedShop);
    }

    public Shopresponsedto getShopById(Long id) {

        Shop shop = shopRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shop not found with id: " + id)
                );

        return shopmapper.toresponse(shop);
    }


    public Shopresponsedto updateShop(Long id, Shoprequestdto shoprequestdto) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user = (User) authentication.getPrincipal();

        Shop shop = shopRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shop not found with id: " + id)
                );

        if (!shop.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("You are not the owner of this shop");
        }

        shop.setShopName(shoprequestdto.getShopName());
        shop.setAddress(shoprequestdto.getAddress());

        Shop updatedShop = shopRepository.save(shop);

        return shopmapper.toresponse(updatedShop);
    }

    public void deleteShop(Long id) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user = (User) authentication.getPrincipal();

        Shop shop = shopRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shop not found with id: " + id)
                );

        if (!shop.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("You are not the owner of this shop");
        }

        shopRepository.delete(shop);
    }
}