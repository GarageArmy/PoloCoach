package com.example.adam.polocoach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Graphicons extends AppCompatActivity {

    BarChart barChart;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphicons);

        db = new DataBase(this);
        barChart = (BarChart) findViewById(R.id.barChart);

        /*ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 10));
        barEntries.add(new BarEntry(1, 15));
        barEntries.add(new BarEntry(2f, 20));
        BarDataSet barDataSet = new BarDataSet(barEntries, "asd");
        ArrayList<String> labels = new ArrayList<>();
        labels.add("1");
        labels.add("2");
        labels.add("3");

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();*/

        createDiag();
    }

    private void createDiag(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        List<ActivityObject> objects = db.getAllActivity();
        ArrayList<String> names = new ArrayList<>();

        for (ActivityObject activity : objects){

        }
    }

}