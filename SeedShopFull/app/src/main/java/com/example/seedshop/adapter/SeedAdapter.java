package com.example.seedshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.seedshop.R;
import com.example.seedshop.model.Seed;
import java.util.List;

public class SeedAdapter extends RecyclerView.Adapter<SeedAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Seed seed);
    }
    private List<Seed> seeds;
    private OnItemClickListener listener;
    public SeedAdapter(List<Seed> seeds, OnItemClickListener listener) {
        this.seeds = seeds;
        this.listener = listener;
    }
    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.item_seed, parent, false);
        return new ViewHolder(v);
    }
    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Seed seed = seeds.get(position);
        holder.name.setText(seed.name);
        holder.price.setText("$" + seed.price);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(seed));
    }
    @Override public int getItemCount() { return seeds.size(); }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.seedName);
            price = view.findViewById(R.id.seedPrice);
        }
    }
}