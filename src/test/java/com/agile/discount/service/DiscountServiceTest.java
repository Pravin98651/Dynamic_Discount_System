package com.agile.discount.service;

import com.agile.discount.model.DiscountRequest;
import com.agile.discount.model.InventoryLevel;
import com.agile.discount.model.Season;
import com.agile.discount.model.UserBehaviour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceTest {

    private DiscountService discountService;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
    }

    @Test
    public void testRegularDiscount_NoDiscount() {
        DiscountRequest request = new DiscountRequest(100.0, UserBehaviour.REGULAR, Season.REGULAR, InventoryLevel.NORMAL);
        assertEquals(0.0, discountService.calculateDiscountPercentage(request), 0.001);
        assertEquals(100.0, discountService.calculateFinalPrice(request), 0.001);
    }

    @Test
    public void testNewUser_BlackFriday_Overstocked() {
        DiscountRequest request = new DiscountRequest(200.0, UserBehaviour.NEW_USER, Season.BLACK_FRIDAY, InventoryLevel.OVERSTOCKED);
        // NEW_USER (10%) + BLACK_FRIDAY (20%) + OVERSTOCKED (5%) = 35%
        assertEquals(0.35, discountService.calculateDiscountPercentage(request), 0.001);
        assertEquals(130.0, discountService.calculateFinalPrice(request), 0.001);
    }

    @Test
    public void testLoyalCustomer_LowStock() {
        DiscountRequest request = new DiscountRequest(50.0, UserBehaviour.LOYAL_CUSTOMER, Season.REGULAR, InventoryLevel.LOW_STOCK);
        // LOYAL_CUSTOMER (15%) + REGULAR (0%) + LOW_STOCK (-5%) = 10%
        assertEquals(0.10, discountService.calculateDiscountPercentage(request), 0.001);
        assertEquals(45.0, discountService.calculateFinalPrice(request), 0.001);
    }

    @Test
    public void testNegativeDiscountScenario() {
        DiscountRequest request = new DiscountRequest(100.0, UserBehaviour.REGULAR, Season.REGULAR, InventoryLevel.LOW_STOCK);
        // REGULAR (0%) + LOW_STOCK (-5%) => should floor to 0%
        assertEquals(0.0, discountService.calculateDiscountPercentage(request), 0.001);
        assertEquals(100.0, discountService.calculateFinalPrice(request), 0.001);
    }
}
