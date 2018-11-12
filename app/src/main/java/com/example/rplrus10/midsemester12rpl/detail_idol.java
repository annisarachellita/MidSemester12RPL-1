package com.example.rplrus10.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rplrus10.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus10.midsemester12rpl.database.MahasiswaModel;

import org.json.JSONObject;

import static android.os.Build.VERSION_CODES.M;

public class detail_idol extends AppCompatActivity {

    ImageView iv_idol;
    TextView tv_name_idol, tv_deskripsi;
    FloatingActionButton fav;
    boolean flag = true;
    Button btntrailer;
    String nama_group;
    String deskripsi;
    String movieID;
    String gambar;
    String name;
    MahasiswaHelper mahasiswaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_idol);

        iv_idol = (ImageView) findViewById(R.id.iv_img_idol);
        tv_name_idol = (TextView) findViewById(R.id.tv_nama_idol);
        tv_deskripsi = (TextView) findViewById(R.id.tv_deskripsi_idol);
        btntrailer = (Button) findViewById(R.id.btntrailer);
        fav = (FloatingActionButton) findViewById(R.id.fav);
        mahasiswaHelper = new MahasiswaHelper(detail_idol.this);
        setTitle("Detail");
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nama_group = bundle.getString("nama_group");
        deskripsi = bundle.getString("deskripsi_group");
        gambar = bundle.getString("gambar");
        movieID = bundle.getString("id");
        tv_name_idol.setText(nama_group);
        tv_deskripsi.setText(deskripsi);
        Glide.with(detail_idol.this)
                .load(gambar)
                .into(iv_idol);
        btntrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://youtu.be/u9Mv98Gr5pY";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    //insert
                    MahasiswaModel model = new MahasiswaModel(nama_group,deskripsi,gambar);
                    mahasiswaHelper.insertTransaction(model);
                    Toast.makeText(getApplicationContext(), "Tersimpan", Toast.LENGTH_SHORT).show();
                    fav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_black_24dp));
                    flag = false;

                } else if (!flag) {
                    int a = mahasiswaHelper.delete(name);
                    fav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border_black_24dp));
                    flag = true;
                }
            }
        });
    }
}