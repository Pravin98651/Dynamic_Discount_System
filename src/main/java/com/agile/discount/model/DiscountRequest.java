package com.agile.discount.model;

public class DiscountRequest {
    private double originalPrice;
    private UserBehaviour userBehaviour;
    private Season season;
    private InventoryLevel inventoryLevel;

    public DiscountRequest() {}

    public DiscountRequest(double originalPrice, UserBehaviour userBehaviour, Season season, InventoryLevel inventoryLevel) {
        this.originalPrice = originalPrice;
        this.userBehaviour = userBehaviour;
        this.season = season;
        this.inventoryLevel = inventoryLevel;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public UserBehaviour getUserBehaviour() {
        return userBehaviour;
    }

    public void setUserBehaviour(UserBehaviour userBehaviour) {
        this.userBehaviour = userBehaviour;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public InventoryLevel getInventoryLevel() {
        return inventoryLevel;
    }

    public void setInventoryLevel(InventoryLevel inventoryLevel) {
        this.inventoryLevel = inventoryLevel;
    }
}
