package com.example.seedshop.model;

import java.io.Serializable;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Seed implements Serializable {
    public String id;          // Firestore document ID
    public String name;
    public String description;
    public double price;

    // No-arg constructor required for Firestore deserialization
    public Seed() {}

    public Seed(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}