package com.example.rplrus10.midsemester12rpl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText txtuser,txtpass;
    Button btnbutton;
    SharedPreferences sharedpreferences;
    private int MAIN_ACTIVITY_REQUEST_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTitle("");

        txtuser = (EditText) findViewById(R.id.txtuser);
        txtpass = (EditText) findViewById(R.id.txtpass);
        btnbutton = (Button) findViewById(R.id.btnbutton);

        btnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtuser.getText().toString().equals("admin") && txtpass.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Login sukses", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this,homeScreen.class);

                    String username = txtuser.getText().toString();
                    sharedpreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("username", username);
                    editor.apply();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SharedPreferences sp = getSharedPreferences("Login",
                MODE_PRIVATE);
        boolean stateValue  = sp.getBoolean("setLoggingOut", false);
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE) {
            if (!stateValue) {
                finish();
            } else {
                updateLoginState(false);
                super.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateLoginState(boolean b) {
    }

}
