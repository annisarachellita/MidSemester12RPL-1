package com.example.rplrus10.midsemester12rpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bumptech.glide.module.LibraryGlideModule;
import com.example.rplrus10.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus10.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

public class favScreen extends AppCompatActivity {

    RecyclerView Rview;
    private MahasiswaHelper mahasiswaHelper;
    public ArrayList<MahasiswaModel> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Rview = (RecyclerView) findViewById(R.id.Rview);
        mahasiswaHelper = new MahasiswaHelper(this);
        model = mahasiswaHelper.getAllData();
        ModelAdapter adapter = new ModelAdapter(getApplicationContext(),model);
        Rview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Rview.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
