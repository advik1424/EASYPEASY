package com.easypeasy.EPS_BACKEND.SHOP.service;

import com.easypeasy.EPS_BACKEND.SHOP.entity.*;
import com.easypeasy.EPS_BACKEND.SHOP.repository.ShopPricingRepository;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private final ShopPricingRepository shopPricingRepository;

    public PricingService(
            ShopPricingRepository shopPricingRepository) {

        this.shopPricingRepository = shopPricingRepository;
    }

    public Integer calculatePrintCost(
            PrintOption option) {

        // ==========================================
        // 1. Validate PrintOption
        // ==========================================

        if (option == null) {
            throw new RuntimeException(
                    "Print options are required"
            );
        }

        // ==========================================
        // 2. Get Order
        // ==========================================

        if (option.getOrder() == null) {
            throw new RuntimeException(
                    "Order is required for pricing"
            );
        }

        Order order =
                option.getOrder();

        // ==========================================
        // 3. Get Document
        // ==========================================

        if (order.getDocument() == null) {
            throw new RuntimeException(
                    "Document is required for pricing"
            );
        }

        Document document =
                order.getDocument();

        // ==========================================
        // 4. Get PDF page count
        // ==========================================

        Integer numberOfPages =
                document.getPageCount();

        if (numberOfPages == null ||
                numberOfPages <= 0) {

            throw new RuntimeException(
                    "Invalid PDF page count"
            );
        }

        // ==========================================
        // 5. Validate copies
        // ==========================================

        if (option.getCopies() == null ||
                option.getCopies() <= 0) {

            throw new RuntimeException(
                    "Copies must be greater than 0"
            );
        }

        // ==========================================
        // 6. Find Shop Pricing
        // ==========================================

        if (order.getShop() == null) {
            throw new RuntimeException(
                    "Shop is required for pricing"
            );
        }

        Long shopId =
                order.getShop().getId();

        ShopPricing pricing =
                shopPricingRepository
                        .findByShopId(shopId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pricing not found for this shop"
                                )
                        );

        // ==========================================
        // 7. Find price per page
        // ==========================================

        Integer pricePerPage;

        if (option.getColorMode() == ColorMode.BW) {

            if (option.getPrintSide() ==
                    PrintSide.SINGLE_SIDE) {

                pricePerPage =
                        pricing.getBwSingleSidePrice();

            } else {

                pricePerPage =
                        pricing.getBwDoubleSidePrice();
            }

        } else {

            if (option.getPrintSide() ==
                    PrintSide.SINGLE_SIDE) {

                pricePerPage =
                        pricing.getColorSingleSidePrice();

            } else {

                pricePerPage =
                        pricing.getColorDoubleSidePrice();
            }
        }

        if (pricePerPage == null) {
            throw new RuntimeException(
                    "Printing price is not configured"
            );
        }

        // ==========================================
        // 8. Pages Per Sheet
        // ==========================================

        int pagesPerSheet = 1;

        if (option.getPagesPerSheet() != null) {

            switch (option.getPagesPerSheet()) {

                case ONE:
                    pagesPerSheet = 1;
                    break;

                case TWO:
                    pagesPerSheet = 2;
                    break;

                case FOUR:
                    pagesPerSheet = 4;
                    break;

                case SIX:
                    pagesPerSheet = 6;
                    break;

                case NINE:
                    pagesPerSheet = 9;
                    break;
            }
        }

        // ==========================================
        // 9. Calculate effective print units
        // ==========================================

        int effectivePrintUnits =
                (int) Math.ceil(
                        (double) numberOfPages
                                / pagesPerSheet
                );

        // ==========================================
        // 10. Double-side adjustment
        // ==========================================

        if (option.getPrintSide() ==
                PrintSide.DOUBLE_SIDE) {

            effectivePrintUnits =
                    (int) Math.ceil(
                            (double) effectivePrintUnits / 2
                    );
        }

        // ==========================================
        // 11. Base printing cost
        // ==========================================

        int total =
                pricePerPage
                        * effectivePrintUnits
                        * option.getCopies();

        // ==========================================
        // 12. Paper Size Extra
        // ==========================================

        if (option.getPaperSize() != null) {

            switch (option.getPaperSize()) {

                case A3:

                    if (pricing.getA3ExtraPrice() != null) {
                        total += pricing.getA3ExtraPrice();
                    }

                    break;

                case A5:

                    if (pricing.getA5ExtraPrice() != null) {
                        total += pricing.getA5ExtraPrice();
                    }

                    break;

                case LETTER:

                    if (pricing.getLetterExtraPrice() != null) {
                        total += pricing.getLetterExtraPrice();
                    }

                    break;

                case LEGAL:

                    if (pricing.getLegalExtraPrice() != null) {
                        total += pricing.getLegalExtraPrice();
                    }

                    break;

                case A4:
                    break;
            }
        }

        // ==========================================
        // 13. Paper Type Extra
        // ==========================================

        if (option.getPaperType() != null) {

            switch (option.getPaperType()) {

                case PREMIUM:

                    if (pricing.getPremiumExtraPrice() != null) {
                        total += pricing.getPremiumExtraPrice();
                    }

                    break;

                case GLOSSY:

                    if (pricing.getGlossyExtraPrice() != null) {
                        total += pricing.getGlossyExtraPrice();
                    }

                    break;

                case PHOTO:

                    if (pricing.getPhotoExtraPrice() != null) {
                        total += pricing.getPhotoExtraPrice();
                    }

                    break;

                case NORMAL:
                    break;
            }
        }

        // ==========================================
        // 14. Binding
        // ==========================================

        if (option.getBindingType() != null) {

            switch (option.getBindingType()) {

                case STAPLE:

                    if (pricing.getStaplePrice() != null) {
                        total += pricing.getStaplePrice();
                    }

                    break;

                case SPIRAL:

                    if (pricing.getSpiralPrice() != null) {
                        total += pricing.getSpiralPrice();
                    }

                    break;

                case COMB:

                    if (pricing.getCombPrice() != null) {
                        total += pricing.getCombPrice();
                    }

                    break;

                case NONE:
                    break;
            }
        }

        // ==========================================
        // 15. Finishing / Lamination
        // ==========================================

        if (option.getFinishingType() != null) {

            switch (option.getFinishingType()) {

                case LAMINATION:

                    if (pricing.getLaminationPrice() != null) {
                        total += pricing.getLaminationPrice();
                    }

                    break;

                case NONE:
                    break;
            }
        }

        // ==========================================
        // 16. Final Total
        // ==========================================

        return total;
    }
}