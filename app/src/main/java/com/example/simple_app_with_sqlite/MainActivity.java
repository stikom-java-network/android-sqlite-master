package com.example.simple_app_with_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.simple_app_with_sqlite.Adapter.BarangAdapter;
import com.example.simple_app_with_sqlite.Database.DatabaseHandler;
import com.example.simple_app_with_sqlite.Model.BarangModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private BarangAdapter barangAdapter;
    private List<BarangModel> barangList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DatabaseHandler db;

    @BindView(R.id.recyclerView)
    RecyclerView reView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        dbHandler.getWritableDatabase();

        db = new DatabaseHandler(this);
        barangList.addAll(db.getAllRecord());

        barangAdapter = new BarangAdapter(this, barangList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        reView.setItemAnimator(new DefaultItemAnimator());
        reView.setLayoutManager(mLayoutManager);
        reView.setAdapter(barangAdapter);
    }

    @OnClick(R.id.floatingActionButton)
    void Tambah(){
        Intent i = new Intent(MainActivity.this, TambahDataActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        barangAdapter.notifyDataSetChanged();
    }
}
