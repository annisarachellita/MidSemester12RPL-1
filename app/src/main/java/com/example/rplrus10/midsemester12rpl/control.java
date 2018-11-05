package com.example.rplrus10.midsemester12rpl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class control extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        if (username.equals("")) {
            Intent intent = new Intent(control.this, login.class);
            startActivity(intent);
            finish();
        } else if (username == username) {
            Intent intent = new Intent(control.this, homeScreen.class);
            startActivity(intent);
            finish();

        }
    }
}
