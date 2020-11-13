package com.bisaai.sqlite;

import android.os.Parcel;
import android.os.Parcelable;

public class Siswa implements Parcelable {

    private String nisn;
    private String nama;
    private String kelas;
    private String alamat;

    public Siswa(String nisn, String nama, String kelas, String alamat) {
        this.nisn = nisn;
        this.nama = nama;
        this.kelas = kelas;
        this.alamat = alamat;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nisn);
        dest.writeString(this.nama);
        dest.writeString(this.kelas);
        dest.writeString(this.alamat);
    }

    public Siswa() {
    }

    // Perhatikan method yang dipanggil pada objek in
    protected Siswa(Parcel in) {
        this.nisn = in.readString();
        this.nama = in.readString();
        this.kelas = in.readString();
        this.alamat = in.readString();
    }

    // Cukup sesuaikan nama objeknya
    public static final Parcelable.Creator<Siswa> CREATOR = new Parcelable.Creator<Siswa>() {
        @Override
        public Siswa createFromParcel(Parcel source) {
            return new Siswa(source);
        }

        @Override
        public Siswa[] newArray(int size) {
            return new Siswa[size];
        }
    };
}
