package com.example.seedmart.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.seedmart.R;
import com.example.seedmart.model.Seed;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.mikephil.charting.charts.BarChart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private FirebaseRecyclerAdapter<Seed, SeedViewHolder> adapter;

    @Override protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.seedRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Query "seeds" node in your Firebase DB
        Query seedQuery = FirebaseDatabase.getInstance()
                .getReference("seeds")
                .orderByKey();

        FirebaseRecyclerOptions<Seed> options =
                new FirebaseRecyclerOptions.Builder<Seed>()
                        .setQuery(seedQuery, Seed.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Seed, SeedViewHolder>(options) {
            @Override
            protected void onBindViewHolder(SeedViewHolder vh, int pos, Seed model) {
                vh.variety.setText(model.variety);
                vh.price.setText("$" + model.pricePerPack);
                vh.itemView.setOnClickListener(v -> {
                    Intent i = new Intent(MainActivity.this, SeedDetailActivity.class);
                    i.putExtra("seedId", model.id);
                    startActivity(i);
                });
            }
            @Override
            public SeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = getLayoutInflater()
                        .inflate(R.layout.item_seed, parent, false);
                return new SeedViewHolder(v);
            }
        };

        rv.setAdapter(adapter);
        adapter.startListening();

        FloatingActionButton fab = findViewById(R.id.chartFab);
        fab.setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new SalesChartFragment())
                        .addToBackStack(null)
                        .commit()
        );
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }
}