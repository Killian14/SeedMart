package com.example.seedshop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import com.example.seedshop.data.AppDatabase;
import com.example.seedshop.model.Order;
import androidx.room.Room;
import android.os.AsyncTask;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    private AppDatabase db;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "seed-shop-db").build();
        int seedId = getIntent().getIntExtra("seedId", -1);
        String seedName = getIntent().getStringExtra("seedName");
        ((TextView)findViewById(R.id.orderSeedName)).setText(seedName);
        EditText qtyInput = findViewById(R.id.quantityInput);
        Button submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(v -> {
            int qty = Integer.parseInt(qtyInput.getText().toString());
            Order order = new Order();
            order.seedId = seedId;
            order.quantity = qty;
            order.timestamp = System.currentTimeMillis();
            new AsyncTask<Void,Void,Void>() {
                @Override protected Void doInBackground(Void... voids) {
                    db.orderDao().insert(order);
                    return null;
                }
            }.execute();
            finish();
        });
    }
}