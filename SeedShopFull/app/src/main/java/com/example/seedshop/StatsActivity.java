package com.example.seedshop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarData;
import com.example.seedshop.data.AppDatabase;
import com.example.seedshop.model.Order;
import androidx.room.Room;
import android.os.AsyncTask;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
    private AppDatabase db;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "seed-shop-db").build();
        BarChart chart = findViewById(R.id.chart);
        new AsyncTask<Void,Void,List<Order>>() {
            @Override protected List<Order> doInBackground(Void... voids) {
                return db.orderDao().getAll();
            }
            @Override protected void onPostExecute(List<Order> orders) {
                Map<Integer,Integer> counts = new HashMap<>();
                for (Order o : orders) {
                    counts.put(o.seedId, counts.getOrDefault(o.seedId, 0) + o.quantity);
                }
                List<BarEntry> entries = new ArrayList<>();
                int i = 0;
                for (Map.Entry<Integer,Integer> e : counts.entrySet()) {
                    entries.add(new BarEntry(i++, e.getValue()));
                }
                BarDataSet set = new BarDataSet(entries, "Seeds Sold");
                BarData data = new BarData(set);
                chart.setData(data);
                chart.invalidate();
            }
        }.execute();
    }
}