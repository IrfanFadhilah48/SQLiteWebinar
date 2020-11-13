package com.bisaai.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

//    static abstract class MyColumns implements BaseColumns {
//        //Menentukan Nama Table dan Kolom
//        static final String NamaTabel = "Mahasiswa";
//        static final String NIM = "NIM";
//        static final String Nama = "Nama_Mahasiswa";
//        static final String Jurusan = "Jurusan";
//        static final String JenisKelamin = "Jenis_Kelamin";
//        static final String TanggalLahir = "Tanggal_Lahir";
//        static final String Alamat = "Alamat";
//    }
    private static final String DATABASE_NAME = "biodatasiswa.db";
    private static final int DATABASE_VERSION = 2;

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL = "CREATE TABLE SISWA" +
                "(nisn TEXT PRIMARY KEY," +
                "nama TEXT NOT NULL, " +
                "kelas TEXT NOT NULL, " +
                "alamat TEXT NOT NULL)";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
