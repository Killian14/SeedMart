package com.example.seedmart.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.seedmart.R;
import com.example.seedmart.model.Seed;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SeedDetailActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_seed_detail);

        String seedId = getIntent().getStringExtra("seedId");
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("seeds").child(seedId);

        TextView variety = findViewById(R.id.variety);
        TextView price   = findViewById(R.id.price);
        Button buyBtn    = findViewById(R.id.buyBtn);

        // Fetch details once
        ref.get().addOnSuccessListener(ds -> {
            Seed sd = ds.getValue(Seed.class);
            variety.setText(sd.variety);
            price.setText("$" + sd.pricePerPack);
            buyBtn.setOnClickListener(v ->
                    // Atomically increment packsSold
                    ref.child("packsSold")
                            .setValue(sd.packsSold + 1)
            );
        });
    }
}