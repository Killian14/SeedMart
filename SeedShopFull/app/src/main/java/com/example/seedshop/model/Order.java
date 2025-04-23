package com.example.seedshop.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int orderId;
    public int seedId;
    public int quantity;
    public long timestamp;
}