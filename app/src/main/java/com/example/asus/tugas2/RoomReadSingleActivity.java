package com.example.asus.tugas2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RoomReadSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText judulBuku = findViewById(R.id.judul_buku);
        EditText kategoriBuku = findViewById(R.id.kategori_buku);
        EditText hargaBuku = findViewById(R.id.harga_buku);
        Button btSubmit = findViewById(R.id.bt_submit);

        judulBuku.setEnabled(false);
        kategoriBuku.setEnabled(false);
        hargaBuku.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Buku buku = (Buku) getIntent().getSerializableExtra("data");
        if(buku!=null){
            judulBuku.setText(buku.getJudulBuku());
            kategoriBuku.setText(buku.getKategoriBuku());
            hargaBuku.setText(buku.getHargaBuku());
        }

    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomReadSingleActivity.class);
    }
}
