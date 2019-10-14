package com.example.asus.tugas2;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RoomCreateActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "bukudb").build();

        final EditText judulBuku   = findViewById(R.id.judul_buku);
        final EditText kategoriBuku   = findViewById(R.id.kategori_buku);
        final EditText hargaBuku  = findViewById(R.id.harga_buku);
        Button btSubmit         = findViewById(R.id.bt_submit);

        final Buku buku = (Buku) getIntent().getSerializableExtra("data");

        if(buku!=null){
            judulBuku.setText(buku.getJudulBuku());
            kategoriBuku.setText(buku.getKategoriBuku());
            hargaBuku.setText(buku.getHargaBuku());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buku.setJudulBuku(judulBuku.getText().toString());
                    buku.setKategoriBuku(kategoriBuku.getText().toString());
                    buku.setHargaBuku(hargaBuku.getText().toString());

                    updateBuku(buku);
                }
            });
        }else{
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Buku b = new Buku();
                    b.setJudulBuku(judulBuku.getText().toString());
                    b.setKategoriBuku(kategoriBuku.getText().toString());
                    b.setHargaBuku(hargaBuku.getText().toString());
                    insertData(b);
                }
            });
        }
    }

    private void updateBuku(final Buku buku){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.bukuDAO().updateBuku(buku);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreateActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertData(final Buku buku){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.bukuDAO().insertBuku(buku);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreateActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomCreateActivity.class);
    }
}