package com.example.rplrus10.midsemester12rpl;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class recyclerHolder extends RecyclerView.ViewHolder {

    public TextView person_name;
    public ImageView imgholder;
    public TextView btndetail;
    public  Button btnshare;
    public Button btnhapus;

    public recyclerHolder( View itemView) {
        super(itemView);

        person_name = (TextView)itemView.findViewById(R.id.person_name);
        imgholder = (ImageView) itemView.findViewById(R.id.imgholder);
        btndetail = (Button) itemView.findViewById(R.id.btndetail);
        btnshare = (Button) itemView.findViewById(R.id.btnshare);
        btnhapus = (Button) itemView.findViewById(R.id.btnhapus);
    }
}
