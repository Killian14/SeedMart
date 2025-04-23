package com.example.seedshop;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.seedshop.adapter.SeedAdapter;
import com.example.seedshop.fragment.SeedDetailFragment;
import com.example.seedshop.model.Seed;
import com.example.seedshop.model.FirebaseDocument;
import com.example.seedshop.model.FirebaseListResponse;
import com.example.seedshop.network.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) Wire up your RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SeedAdapter(new ArrayList<>(), this::showDetail);
        recyclerView.setAdapter(adapter);

        // 2) Fetch your seeds from Firestore via REST
        fetchSeeds();
    }

    private void fetchSeeds() {
        FirestoreClient.getService()
                .listSeeds("YOUR_PROJECT_ID", "YOUR_API_KEY")
                .enqueue(new Callback<FirebaseListResponse>() {
                    @Override
                    public void onResponse(Call<FirebaseListResponse> call,
                                           Response<FirebaseListResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Seed> seeds = new ArrayList<>();
                            for (FirebaseDocument doc : response.body().documents) {
                                String idStr = doc.name.substring(doc.name.lastIndexOf('/') + 1);
                                Seed s = new Seed();
                                // parse or assign as needed
                                try {
                                    s.id = Integer.parseInt(idStr);
                                } catch (NumberFormatException e) {
                                    s.id = 0; // or handle differently
                                }
                                s.name = doc.fields.get("name").stringValue;
                                s.description = doc.fields.get("description").stringValue;
                                s.price = doc.fields.get("price").doubleValue;
                                seeds.add(s);
                            }
                            adapter.updateData(seeds);
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "No seeds found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FirebaseListResponse> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(MainActivity.this,
                                "Failed to load seeds", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showDetail(Seed seed) {
        SeedDetailFragment frag = new SeedDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(SeedDetailFragment.ARG_SEED, seed);
        frag.setArguments(args);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, frag);
        ft.addToBackStack(null);
        ft.commit();

        findViewById(R.id.fragmentContainer).setVisibility(View.VISIBLE);
    }
}
