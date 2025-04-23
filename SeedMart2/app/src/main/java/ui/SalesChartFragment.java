package com.example.seedmart.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.seedmart.R;
import com.example.seedmart.model.Seed;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;

public class SalesChartFragment extends Fragment {
    private BarChart chart;

    @Nullable @Override
    public View onCreateView(LayoutInflater inf, ViewGroup c, Bundle s) {
        View v = inf.inflate(R.layout.fragment_sales_chart, c, false);
        chart = v.findViewById(R.id.barChart);

        FirebaseDatabase.getInstance()
                .getReference("seeds")
                .get().addOnSuccessListener(ds -> {
            ArrayList<BarEntry> entries = new ArrayList<>();
            ArrayList<String> labels  = new ArrayList<>();
            int i = 0;
            for (DataSnapshot snap : ds.getChildren()) {
                Seed sd = snap.getValue(Seed.class);
                entries.add(new BarEntry(i, sd.packsSold));
                labels.add(sd.variety);
                i++;
            }
            BarDataSet set = new BarDataSet(entries, "Packs Sold");
            chart.setData(new BarData(set));
            chart.getXAxis().setValueFormatter((value, axis) ->
                    labels.get((int)value));
            chart.getDescription().setEnabled(false);
            chart.invalidate();
        });

        return v;
    }
}