package com.example.lab4var18;

public class Paint extends Buildingmaterials {
    private int volume;
    private boolean isFireproof;
    public Paint(String type, String brand, double price, int sum, int volume, boolean isFireproof, String buildingMaterialsType) {
        super(type, brand, price, sum, buildingMaterialsType);
        this.volume=volume;
        this.isFireproof=isFireproof;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public boolean isFireproof() {
        return isFireproof;
    }
    public void setFireproof(boolean fireproof) {
        isFireproof = fireproof;
    }
    public String toString() {
        return "тип - " + getType() + "; производитель - " + getBrand() + "; цена - " + getPrice() + "; количество - "
                + getSum() + "; объем - " + getVolume() + "; жароустойчивая - " + isFireproof() + "\n";
    }
}