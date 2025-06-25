package com.ovidonius.models.enums;

public enum DiscountType {
    NONE(1.0),
    STUDENT(0.75),
    SOLDIER(0.3),
    PENSIONER(0.7);

    private final double multiplier;

    DiscountType(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}