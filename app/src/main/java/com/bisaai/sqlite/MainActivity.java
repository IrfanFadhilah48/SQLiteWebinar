package com.bisaai.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataHelper dataHelper;
    private FloatingActionButton fab;
    private Adapter adapter;
    private RecyclerView rvMain;
    private ArrayList<Siswa> dataSiswa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHelper = new DataHelper(this);
        fab = findViewById(R.id.fabMain);
        rvMain = findViewById(R.id.rvMain);
        setRecyclerView();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), InsertUpdateActivity.class);
            intent.putExtra(InsertUpdateActivity.DATAFLAG, InsertUpdateActivity.TAMBAHDATA);
            startActivity(intent);
        });

        setData();

    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(this, dataSiswa);
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void clicked(Siswa data) {
                Toast.makeText(
                        getApplicationContext(),
                        data.getNama(),
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(getApplication(), InsertUpdateActivity.class);
                intent.putExtra(InsertUpdateActivity.DATAFLAG, InsertUpdateActivity.EDITDATA);
                intent.putExtra(InsertUpdateActivity.DATA, data);
                startActivity(intent);
            }

            @Override
            public void deleteData(Siswa data, int position) {
                SQLiteDatabase delete = dataHelper.getWritableDatabase();
                String sql = "DELETE FROM SISWA WHERE nisn = " + data.getNisn();
                delete.execSQL(sql);
                SQLiteDatabase ReadData = dataHelper.getReadableDatabase();
                Cursor cursor = ReadData.rawQuery("SELECT * FROM SISWA", null);
                Log.e("datanyaDelete", String.valueOf(cursor.getCount()));
                cursor.close();
                dataSiswa.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvMain.setLayoutManager(layoutManager);
        rvMain.addItemDecoration(decoration);
        rvMain.setAdapter(adapter);
    }

    private void setData() {
        SQLiteDatabase ReadData = dataHelper.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM SISWA", null);
        if (cursor.moveToFirst()){
            Log.e("datanyaSize", String.valueOf(cursor.getCount()));
            for (int i = 0; i < cursor.getCount(); i++){
                cursor.moveToPosition(i);
                dataSiswa.add(
                        new Siswa(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)
                        )
                );
            }
            Log.e("datanyaSiswa", String.valueOf(dataSiswa.size()));
            adapter.notifyDataSetChanged();
        }
        cursor.close();
        Toast.makeText(this, dataHelper.getDatabaseName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dataSiswa.clear();
        setData();
    }
}