package com.example.rplrus10.midsemester12rpl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerHolder> {

    private ArrayList<idol> idolArrayList ;
    Context context;

    public recyclerAdapter(Context context, ArrayList<idol> idolArrayList){
        this.context = context;
        this.idolArrayList = idolArrayList;
    }


    public recyclerHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        recyclerHolder rcv = new recyclerHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final recyclerHolder holder,final int position) {
        final idol idol = idolArrayList.get(position);
        Glide.with(context)
                .load(idol.getGambar())
                .into(holder.imgholder);
        holder.person_name.setText(idolArrayList.get(position).getNama_group());
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nama_group = idolArrayList.get(position).getNama_group();
                final String deskripsi_group = idolArrayList.get(position).getDeskripsi_group();
                final String gambar = idolArrayList.get(position).getGambar();
                final String tanggal = idolArrayList.get(position).getTanggal();
                final String id = idolArrayList.get(position).getId();
                Intent i = new Intent(context.getApplicationContext(),detail_idol.class);
                i.putExtra("nama_group",nama_group);
                i.putExtra("deskripsi_group",deskripsi_group);
                i.putExtra("gambar",gambar);
                i.putExtra("tanggal",tanggal);
                i.putExtra("id",id);
                context.startActivity(i);
            }
        });
        holder.btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"This is my application");
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });
        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        context);
                builder.setTitle("Delete");
                builder.setCancelable(true);
                builder.setMessage("are you sure to delete this item?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        idolArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,idolArrayList.size());
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+idolArrayList.size());
        return idolArrayList.size();
    }
}
