package com.agile.discount.service;

import com.agile.discount.model.DiscountRequest;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public double calculateDiscountPercentage(DiscountRequest request) {
        double discount = 0.0;

        // User Behaviour Discount
        switch (request.getUserBehaviour()) {
            case NEW_USER:
                discount += 0.10;
                break;
            case LOYAL_CUSTOMER:
                discount += 0.15;
                break;
            case REGULAR:
            default:
                break;
        }

        // Seasonal Discount
        switch (request.getSeason()) {
            case BLACK_FRIDAY:
                discount += 0.20;
                break;
            case SUMMER_SALE:
                discount += 0.10;
                break;
            case HOLIDAY:
                discount += 0.05;
                break;
            case REGULAR:
            default:
                break;
        }

        // Inventory based adjustment
        switch (request.getInventoryLevel()) {
            case OVERSTOCKED:
                discount += 0.05;
                break;
            case LOW_STOCK:
                discount -= 0.05;
                break;
            case NORMAL:
            default:
                break;
        }

        // Ensure discount is within bounds (0% to 100%)
        if (discount < 0) {
            discount = 0.0;
        } else if (discount > 1.0) {
            discount = 1.0;
        }

        return discount;
    }

    public double calculateFinalPrice(DiscountRequest request) {
        double discountPercentage = calculateDiscountPercentage(request);
        return request.getOriginalPrice() * (1.0 - discountPercentage);
    }
}
