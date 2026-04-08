package com.agile.discount.controller;

import com.agile.discount.model.DiscountRequest;
import com.agile.discount.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculateDiscount(@RequestBody DiscountRequest request) {
        double finalPrice = discountService.calculateFinalPrice(request);
        double discountPercentage = discountService.calculateDiscountPercentage(request);
        
        Map<String, Object> response = new HashMap<>();
        response.put("originalPrice", request.getOriginalPrice());
        response.put("discountPercentage", discountPercentage * 100);
        response.put("finalPrice", finalPrice);
        
        return ResponseEntity.ok(response);
    }
}
