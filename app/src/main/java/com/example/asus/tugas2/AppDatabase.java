package com.example.asus.tugas2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.asus.tugas2.BukuDAO;
import com.example.asus.tugas2.Buku;

@Database(entities = {Buku.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BukuDAO bukuDAO();
}
