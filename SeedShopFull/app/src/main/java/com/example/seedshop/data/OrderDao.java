package com.example.seedshop.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.seedshop.model.Order;

@Dao
public interface OrderDao {
    @Insert void insert(Order order);
    @Query("SELECT * FROM `Order`") List<Order> getAll();
}