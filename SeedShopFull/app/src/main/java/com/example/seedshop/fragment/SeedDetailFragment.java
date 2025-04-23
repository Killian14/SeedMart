package com.example.seedshop.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import com.example.seedshop.R;
import com.example.seedshop.model.Seed;
import android.content.Intent;
import com.example.seedshop.OrderActivity;

public class SeedDetailFragment extends Fragment {
    public static final String ARG_SEED = "seed";
    private Seed seed;
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_seed_detail, container, false);
        seed = (Seed) getArguments().getSerializable(ARG_SEED);
        TextView name = v.findViewById(R.id.detailName);
        TextView desc = v.findViewById(R.id.detailDesc);
        TextView price = v.findViewById(R.id.detailPrice);
        Button orderBtn = v.findViewById(R.id.orderButton);
        name.setText(seed.name);
        desc.setText(seed.description);
        price.setText("$" + seed.price);
        orderBtn.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), OrderActivity.class);
            i.putExtra("seedId", seed.id);
            i.putExtra("seedName", seed.name);
            startActivity(i);
        });
        return v;
    }
}