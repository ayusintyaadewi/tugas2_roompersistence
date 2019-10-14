package com.example.asus.tugas2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tabelBuku")
public class Buku implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int bukuId;

    @ColumnInfo(name = "judul_buku")
    public String judulBuku;

    @ColumnInfo(name = "kategori_buku")
    public String kategoriBuku;

    @ColumnInfo(name = "harga_buku")
    public String hargaBuku;

    public int getBukuId() {
        return bukuId;
    }

    public void setBukuId(int bukuId) {
        this.bukuId = bukuId;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getKategoriBuku() {
        return kategoriBuku;
    }

    public void setKategoriBuku(String kategoriBuku) {
        this.kategoriBuku = kategoriBuku;
    }

    public String getHargaBuku() {
        return hargaBuku;
    }

    public void setHargaBuku(String hargaBuku) {
        this.hargaBuku = hargaBuku;
    }
}
