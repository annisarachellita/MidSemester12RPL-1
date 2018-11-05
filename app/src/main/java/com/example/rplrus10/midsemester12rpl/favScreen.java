package com.example.rplrus10.midsemester12rpl;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.module.LibraryGlideModule;
import com.example.rplrus10.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus10.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

public class favScreen extends AppCompatActivity {

    RecyclerView Rview;
    private MahasiswaHelper mahasiswaHelper;
    public ArrayList<MahasiswaModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_screen);
        Rview = (RecyclerView) findViewById(R.id.Rview);
        mahasiswaHelper = new MahasiswaHelper(this);
        mahasiswaHelper.open();
        models = mahasiswaHelper.getAllData();
        ModelAdapter adapter = new ModelAdapter(getApplicationContext(),models);
        mahasiswaHelper.close();
        Rview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Rview.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
