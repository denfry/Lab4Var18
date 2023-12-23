package com.example.lab4var18;

public class Buildingmaterials {
    private String type;
    private String brand;
    private double price;
    private int sum;
    private String buildingMaterialsType;

    public Buildingmaterials(String type, String brand, double price, int sum, String buildingMaterialsType) {
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.sum = sum;
        this.buildingMaterialsType = buildingMaterialsType;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getSum() {
        return sum;
    }

    public String getBuildingMaterialsType() {
        return buildingMaterialsType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setBuildingMaterialsType(String buildingMaterialsType) {
        this.buildingMaterialsType = buildingMaterialsType;
    }

    public String toString() {
        return "Тип товара - " + getType() + "; производитель - " + getBrand() + "; цена - " + getPrice() +
                "; количество - " + getSum() + "; тип - " + getBuildingMaterialsType() + "\n";
    }

}

