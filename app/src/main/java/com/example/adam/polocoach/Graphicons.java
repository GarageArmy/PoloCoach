package com.example.adam.polocoach;

import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Graphicons extends AppCompatActivity {
    BarChart barChart;
    ArrayList<String> names = new ArrayList<>();
    Random random;
    ArrayList<BarEntry> barEntries;

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBase(this);
        barChart = (BarChart) findViewById(R.id.bargraph);

        createRandomBarGraph();

    }

    public void createRandomBarGraph(){
/*
        List<ActivityObject> activityObjects = db.getAllActivity();


        ArrayList<BarEntry> entries = new ArrayList<>();


        for (ActivityObject activity : activityObjects){
            if (activity.getActivityName() == "goal") {
                if (!names.contains(activity.getPlayer())) {
                    names.add(activity.getPlayer());
                    System.out.println(activity.getPlayer());
                }
            }
        }
        int[] goals = new int[names.size()];
        for (ActivityObject activity : activityObjects){
            if (activity.getActivityName() == "goal") {
                int l = 0;
                while (names.get(l) != activity.getPlayer())
                    l++;
                goals[l]++;
            }
        }

        for (int i = 0; i < names.size(); i++){
            entries.add(new BarEntry(goals[i], i));
        }


        BarDataSet barDataSet = new BarDataSet(barEntries,"Players");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
*/
    }


}