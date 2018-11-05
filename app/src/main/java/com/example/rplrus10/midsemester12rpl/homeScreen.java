package com.example.rplrus10.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class homeScreen extends AppCompatActivity {

    Menu menu;
    LinearLayout listdata,listload;
    idol idol;
    Button btnshare;
    public ArrayList<idol> idolArrayList;
    RecyclerView gv_idol;

    //    String[][] idol = new String[][]{
//            {"EXO", "EXO adalah grup penyanyi pria Korea Selatan-Tiongkok yang berbasis di Seoul. Dibentuk oleh S.M. Entertainment pada tahun 2011, grup ini debut pada tahun 2012 dengan 12 anggota yang terbagi menjadi dua subgrup, EXO-K dan EXO-M, dengan masing-masing subgrup tersebut menyanyikan lagu dalam bahasa Korea dan Mandarin. EXO meraih puncak kepopuleran dengan dirilisnya album pertama mereka, XOXO (2013), yang menelurkan singel hit \"Growl\". XOXO sukses baik secara kritikal maupun komersial, serta berhasil memenangkan penghargaan 'Disk Daesang' di Golden Disk Awards ke-28 dan 'Album Terbaik Tahun Ini' di Mnet Asian Music Awards ke-15. Album ini terjual lebih dari satu juta kopi, menjadikan EXO sebagai musisi Korea dengan penjualan terbanyak dalam kurun waktu 12 tahun terakhir."},
//            {"BTS", "Bangtan Boys atau dikenal juga sebagai BTS adalah grup penyanyi pria asal Korea Selatan yang dibentuk oleh label rekaman Big Hit Entertainment. Grup ini beranggotakan tujuh orang, yakni RM, Jin, Suga, J-Hope, Jimin, V dan Jungkook.[1] Mereka debut pada 13 Juni 2013 dengan singel \"No More Dream\" dari album pertama 2 Cool 4 Skool yang membawa grup ini meraih penghargaan 'New Artist of the Year' di MelOn Music Awards dan Golden Disk Awards 2013, serta Seoul Music Awards 2014. Setahun setelah debut, mereka menerima penghargaan bonsang untuk album Dark & Wild dan The Most Beautiful Moment In Life."},
//            {"GOT7", "Got7 adalah grup musik pria asal Korea Selatan yang dibentuk oleh JYP Entertainment. Grup ini terdiri dari tujuh anggota, yakni JB, Mark, Jinyoung, Jackson, Youngjae, BamBam dan Yugyeom. Got7 debut pada Januari 2014 dengan dirilisnya album mini pertama mereka, Got It?, yang berhasil menduduki posisi nomor dua di Gaon Album Chart dan nomor satu di Billboard World Albums Chart."},
//            {"NCT","NCT adalah boygrup Korea Selatan yang dibentuk oleh S.M. Entertainment.[1][2] NCT adalah singkatan dari Neo Culture Technology, yang dijelaskan oleh pendiri S.M. Entertainment, Lee Soo-man bahwa konsep grup ini mempunyai anggota yang tak terhingga[3][4][5] yang terbagi menjadi beberapa sub-unit berbasis di berbagai kota di dunia.Unit dengan enam anggota NCT U memulai debut pada April 2016 dengan singel digital \"The 7th Sense\", dan \"Without You\". Unit kedua, NCT 127 berbasis di Seoul, memulai debut pada Juli 2016 dengan album mini NCT #127.[8] Unit ketiga, NCT Dream memulai debut pada Agustus 2016 dengan singel digital Chewing Gum.[7] Unit berikutnya diharapkan akan debut di Tiongkok, Jepang, Thailand, Vietnam dan Indonesia."},
//            {"INFINITE","Infinite adalah boyband asal Korea Selatan di bawah label Woollim Entertainment, yang juga rumah bagi bakat lain seperti Lovelyz dan Golden Child Kelompok ini terdiri dari 6 anggota: (Sunggyu, Dongwoo, Woohyun, Sungyeol, L, dan Sungjong). Mereka dikenal karena tarian mereka sangat disinkronkan serta 'Scorpion Dance' mereka di MV \"Before The Dawn\". Dengan tahun 2011 sebagai tahun pelarian mereka, Infinite terus lanjut sampai akhirnya sukses dan mendapatkan popularitas lebih di antara kelompok lainnya di dua negara, Korea Selatan dan Jepang"},
//            {"2PM", "2PM adalah boy grup Korea Selatan yang dibentuk oleh JYP Entertainment. Anggota saat ini adalah Jun. K (sebelumnya dikenal sebagai Junsu), Nichkhun, Taecyeon, Wooyoung, Junho dan Chansung. Mantan pemimpin grup, Jay Park secara resmi meninggalkan grup ini pada awal tahun 2010.Para anggota 2PM memulai karier saat Park Jin-young membentuk grup musik beranggotakan sebelas orang yang dikenal sebagai One Day. Grup musik ini kemudian dibagi menjadi dua, yaitu 2PM dan 2AM. 2PM melakukan debut dengan lagu \"10 Jeom Manjeome 10 Jeom\" (10점 만점에 10점, terj. \"10 Points Out of 10 Points\"), yang menampilkan gaya tarian akrobatik mereka.Para anggota 2PM memulai karier saat Park Jin-young membentuk grup musik beranggotakan sebelas orang yang dikenal sebagai One Day. Grup musik ini kemudian dibagi menjadi dua, yaitu 2PM dan 2AM. yang menampilkan gaya tarian akrobatik mereka."},
//            {"BIG BANG","Big Bang adalah boy band asal Korea Selatan yang dibentuk oleh YG Entertainment. Grup ini debut secara resmi pada 19 Agustus 2006 dengan lima anggota, yakni G-Dragon, T.O.P, Taeyang, Daesung dan Seungri. Didominasi dengan aliran hip hop, debut mereka pun cukup sukses dengan album pertama Bigbang Vol.1 yang terjual sebanyak 48.000 kopi. Kesuksesan mereka melesat setelah dirilisnya album mini pertama mereka, Always, dengan singel \"Lies\" (Hangul: 거짓말; RR: Geojitmal). Lagu ini merajai tangga lagu Korea selama tujuh minggu berturut-turut[3] dan membawa grup ini meraih penghargaan 'Song of the Year' di Mnet Korean Music Festival 2007."},
//            {"GFRIEND", "GFriend adalah sebuah grup vokal perempuan Korea Selatan beranggotakan enam orang yang dibentuk oleh Source Music pada 2015.[2] Mereka membuat debut mereka dengan EP Season of Glass pada 15 Januari 2015.[3] GFriend meraih skor impresif di berbagai tangga musik dan program musik tepat 12 hari semenjak debut mereka."},
//            {"BLACPINK", "BLACKPINK atau BLΛƆKPIИK, adalah girl grup Korea Selatan yang dibentuk tahun 2016 oleh YG Entertainment. Grup ini terdiri dari empat orang: Jisoo, Jennie, Rosé dan Lisa.[1] Keempatnya secara resmi debut pada 8 Agustus 2016 dengan album singel mereka, Square One."},
//            {"TWICE","TWICE, adalah grup musik K-pop mancanegara yang dibentuk oleh JYP Entertainment melalui acara realitas SIXTEEN pada tahun 2015. TWICE terdiri dari sembilan anggota: Nayeon, Jeongyeon, Momo, Sana, Jihyo, Mina, Dahyun, Chaeyoung, dan Tzuyu.[1] Mereka debut pada tanggal 20 Oktober 2015, dengan album mini The Story Begins.TWICE meraih ketenaran pada tahun 2016 dengan lagu \"Cheer Up\" yang menduduki peringkat 1 pada Gaon Digital Chart dan menjadi singel dengan penjualan terbaik di tahun yang sama. Lagu ini juga memenangkan gelar Song of the Year pada acara penghargaan musik utama seperti Melon Music Awards dan Mnet Asian Music Awards.[3][4][5] Lagu yang lain, \"TT\" dari album mini ketiga mereka Twicecoaster: Lane 1, menduduki puncak tangga lagu selama empat minggu berturut-turut. Album ini merupakan album grup wanita dengan penjualan terbaik untuk tahun 2016, dan terjual sebanyak 350,852 kopi pada akhir tahun.[6][7] Hanya dalam waktu 19 bulan setelah debut, TWICE telah menjual lebih dari 1.2 juta kopi, melalui empat album mini dan satu album spesial."}
//
//    };
//    int [] image_idol  = new int[]{
//            R.drawable.images, R.drawable.bts, R.drawable.got7, R.drawable.nct, R.drawable.infinitie, R.drawable.duapm,
//            R.drawable.bigbang, R.drawable.gfriend, R.drawable.blackpink, R.drawable.twice
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        gv_idol = (RecyclerView) findViewById(R.id.gv_idol);
        listdata=(LinearLayout) findViewById(R.id.listdata);
        listload=(LinearLayout) findViewById(R.id.listload);
        new ambildata().execute();
//        idolArrayList = new ArrayList<idol>();
//        for (int i = 0; i < idol.length; i++) {
//            data_idol = new idol();
//            data_idol.setNama_group(idol[i][0]);
//            data_idol.setGambar(image_idol[i]);
//            data_idol.setDeskripsi_group(idol[i][1]); dffff
//            idolArrayList.add(data_idol);
//        }
//        gv_idol = findViewById(R.id.gv_idol);
//        gv_idol.setLayoutManager(new LinearLayoutManager(homeScreen.this));
//        recyclerAdapter adapter = new recyclerAdapter (homeScreen.this,idolArrayList);
//        gv_idol.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                moveTaskToBack(true);
                SharedPreferences myPrefs = getSharedPreferences("Login",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(homeScreen.this,
                        homeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            case R.id.menu1:
                Intent intent1 = new Intent(this,favScreen.class);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ambildata extends AsyncTask<Void, Void, JSONObject> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;

            try {
                String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a5db9144717c709a534910444763b21f";
                System.out.println("url ku " + url);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"
                ), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                System.out.println("json nya " + json);
                jsonObject = new JSONObject(json);
            } catch (Exception e) {
                System.out.println("json error : " + e.toString());
                jsonObject = null;
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            //System.out.println("Hasilnya adalah " +jsonObject.toString());
            listload.setVisibility(View.GONE);
            listdata.setVisibility(View.VISIBLE);
            if (jsonObject == null) {

            }else if (jsonObject!=null){
                try {
                    JSONArray Hasiljson = jsonObject.getJSONArray("results");
                    idolArrayList = new ArrayList<>();
                    for (int i = 0; i < Hasiljson.length(); i++) {
                        idol = new idol();
                        String urlku = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/";
                        String gambar = Hasiljson.getJSONObject(i).getString("poster_path");
                        idol.setNama_group(Hasiljson.getJSONObject(i).getString("title"));
                        idol.setGambar(urlku + gambar);
                        idol.setDeskripsi_group(Hasiljson.getJSONObject(i).getString("overview"));
                        idolArrayList.add(idol);
                    }
                    //pasang data arraylist ke listview
                    recyclerAdapter adapter = new recyclerAdapter(homeScreen.this, idolArrayList);
                    gv_idol.setLayoutManager(new LinearLayoutManager(homeScreen.this));
                    gv_idol.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
