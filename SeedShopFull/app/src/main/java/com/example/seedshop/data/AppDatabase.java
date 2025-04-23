package com.example.seedshop.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.seedshop.model.Order;

@Database(entities = {Order.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract OrderDao orderDao();
}