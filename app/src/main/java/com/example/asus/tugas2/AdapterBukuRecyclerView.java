package com.example.asus.tugas2;

import android.app.Activity;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBukuRecyclerView extends RecyclerView.Adapter<AdapterBukuRecyclerView.ViewHolder>{

    private ArrayList<Buku> daftarBuku;
    private Context context;
    private AppDatabase db;

    public AdapterBukuRecyclerView(ArrayList<Buku> bukus, Context ctx){
        daftarBuku = bukus;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "bukudb").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_namabuku);
            cvMain = v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buku, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = daftarBuku.get(position).getJudulBuku();

        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buku buku = db.bukuDAO().selectBukuDetail(daftarBuku.get(position).getBukuId());
                context.startActivity(RoomReadSingleActivity.getActIntent((Activity) context).putExtra("data", buku));
            }
        });

        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Action");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.bt_edit_data);
                Button delButton = dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onEditBuku(position);
                            }
                        }
                );

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onDeteleBuku(position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    private void onDeteleBuku(int position){
        db.bukuDAO().deleteBuku(daftarBuku.get(position));
        daftarBuku.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarBuku.size());
    }

    private void onEditBuku(int position){
        context.startActivity(RoomCreateActivity.getActIntent((Activity) context).putExtra("data", daftarBuku.get(position)));
    }

    @Override
    public int getItemCount() {
        return daftarBuku.size();
    }
}
