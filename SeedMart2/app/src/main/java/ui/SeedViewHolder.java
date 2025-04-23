package com.example.seedmart.ui;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.seedmart.R;

public class SeedViewHolder extends RecyclerView.ViewHolder {
    TextView variety, price;

    public SeedViewHolder(View itemView) {
        super(itemView);
        variety = itemView.findViewById(R.id.seedVariety);
        price   = itemView.findViewById(R.id.seedPrice);
    }
}