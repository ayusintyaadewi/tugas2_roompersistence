package com.example.asus.tugas2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asus.tugas2.Buku;

@Dao
public interface BukuDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBuku(Buku buku);

    @Update
    int updateBuku(Buku buku);

    @Delete
    int deleteBuku(Buku buku);

    @Query("SELECT * FROM tabelBuku")
    Buku[] selectAllBukus();

    @Query("SELECT * FROM tabelBuku WHERE bukuId = :id LIMIT 1")
    Buku selectBukuDetail(int id);
}
