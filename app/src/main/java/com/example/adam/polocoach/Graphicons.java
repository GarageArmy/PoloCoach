package com.example.adam.polocoach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Home").withIcon(R.drawable.ball).withIdentifier(1);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Statisztika");

        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(item1)//.withAccountHeader(headerResult)
                .addDrawerItems(item2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent a = getIntent();
                        switch((int)drawerItem.getIdentifier()){
                            case 1: if (a != new Intent("com.example.adam.polocoach.InGame")) finish();
                                break;
                            case 2: {
                                if (a != new Intent("com.example.adam.polocoach.InGame")) {
                                    Intent intent = new Intent("com.example.adam.polocoach.Graphicons");
                                    startActivity(intent);
                                }
                            } break;

                        }
                        return true;
                    }
                })
                .build();

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