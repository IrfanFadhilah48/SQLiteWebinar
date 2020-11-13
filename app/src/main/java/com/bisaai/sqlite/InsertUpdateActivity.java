package com.bisaai.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class InsertUpdateActivity extends AppCompatActivity {

    public static final String DATAFLAG = "DATAFLAG";
    public static final String TAMBAHDATA = "TAMBAHDATA";
    public static final String EDITDATA = "EDITDATA";
    public static final String DATA = "DATA";
    private DataHelper dataHelper;
    private Button button;
    private TextInputEditText etNisn, etNama, etKelas, etAlamat;
    private ConstraintLayout clContent;
    private String dataFlag;
    private Siswa dataSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_update);

        dataHelper = new DataHelper(this);
        Log.e("dataya", dataHelper.getDatabaseName());
        button = findViewById(R.id.btnSimpan);
        etNisn = findViewById(R.id.etNisn);
        etNama = findViewById(R.id.etNama);
        etKelas = findViewById(R.id.etKelas);
        etAlamat = findViewById(R.id.etAlamat);
        clContent = findViewById(R.id.clTambahEdit);

        dataFlag = getIntent().getStringExtra(DATAFLAG);
        if (dataFlag.equals(EDITDATA)){
            dataSiswa = getIntent().getParcelableExtra(DATA);
            etNisn.setText(dataSiswa.getNisn());
            etNama.setText(dataSiswa.getNama());
            etKelas.setText(dataSiswa.getKelas());
            etAlamat.setText(dataSiswa.getAlamat());
            etNisn.setFocusable(false);
            etNisn.setEnabled(false);
        }

        button.setOnClickListener(v ->{
            if (etNisn.getText().toString().isEmpty()){
                Toast.makeText(this, "Harap isikan NISN", Toast.LENGTH_SHORT).show();
            }
            else if (etNama.getText().toString().isEmpty()){
                Toast.makeText(this, "Harap isikan Nama", Toast.LENGTH_SHORT).show();
            }
            else if (etKelas.getText().toString().isEmpty()){
                Toast.makeText(this, "Harap isikan Kelas", Toast.LENGTH_SHORT).show();
            }
            else if (etAlamat.getText().toString().isEmpty()){
                Toast.makeText(this, "Harap isikan Alamat", Toast.LENGTH_SHORT).show();
            }
            else{
                if (dataFlag.equals(EDITDATA)){
                    updateData();
                }
                else {
                    saveData();
                }
            }
        });
    }

    private void saveData(){
        ContentValues values = new ContentValues();
        values.put("nisn", etNisn.getText().toString());
        values.put("nama", etNama.getText().toString());
        values.put("kelas", etKelas.getText().toString());
        values.put("alamat", etAlamat.getText().toString());
        SQLiteDatabase create = dataHelper.getWritableDatabase();
        create.insert("SISWA", null, values);
        Toast.makeText(
                this,
                "Berhasil Menambahkan Data",
                Toast.LENGTH_SHORT
        ).show();
        etNisn.setText("");
        etNama.setText("");
        etAlamat.setText("");
        etKelas.setText("");
        etKelas.clearFocus();
        clContent.requestFocus();
    }

    private void updateData() {
        SQLiteDatabase update = dataHelper.getWritableDatabase();
        String sql = "UPDATE SISWA SET nama = '" + etNama.getText().toString() + "', " +
                " kelas = '" + etKelas.getText().toString() + "', "+
                " alamat = '" + etAlamat.getText().toString() + "'"+
                " WHERE nisn = '" + etNisn.getText().toString() + "'";
//        String updateQuery = "UPDATE SISWA SET " +
//                " nama ='" + etNama.getText().toString() + "', "
//                + COLUMN_ADDRESS + "='" + address + "'"
//                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("dataSQLUpdate", sql);
        update.execSQL(sql);
        finish();
    }
}