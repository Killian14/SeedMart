package com.example.seedmart.model;

public class Seed {
    public String id;
    public String variety;
    public double pricePerPack;
    public int packsSold;

    // Required empty constructor for Firebase
    public Seed() {}

    public Seed(String id, String variety, double pricePerPack, int packsSold) {
        this.id = id;
        this.variety = variety;
        this.pricePerPack = pricePerPack;
        this.packsSold = packsSold;
    }
}