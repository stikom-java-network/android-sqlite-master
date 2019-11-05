package com.example.simple_app_with_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simple_app_with_sqlite.Database.DatabaseHandler;
import com.example.simple_app_with_sqlite.Model.BarangModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahDataActivity extends AppCompatActivity {

    @BindView(R.id.nama_barang)
    EditText textNamaBarang;
    @BindView(R.id.kode_barang)
    EditText textKodeBarang;
    @BindView(R.id.satuan_barang)
    EditText textSatuanBarang;
    @BindView(R.id.harga_beli)
    EditText textHargaBeli;
    @BindView(R.id.harga_jual)
    EditText textHargaJual;
    @BindView(R.id.kategori_barang)
    EditText textKategoriBarang;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String namaBarang;
    String kodeBarang;
    String satuanBarang;
    String kategoriBarang;
    int hargaBeli;
    int hargaJual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.tambah)
    void tambah(){
        namaBarang = textNamaBarang.getText().toString();
        kodeBarang = textKodeBarang.getText().toString();
        satuanBarang = textSatuanBarang.getText().toString();
        kategoriBarang = textKategoriBarang.getText().toString();
        hargaBeli = Integer.parseInt(textHargaBeli.getText().toString());
        hargaJual = Integer.parseInt(textHargaJual.getText().toString());

        BarangModel userModel = new BarangModel(kodeBarang,
                namaBarang, satuanBarang, kategoriBarang, hargaBeli, hargaJual);

        DatabaseHandler db = new DatabaseHandler(this);

        if (db.addRecord(userModel) == 1){
            Toast.makeText(this, "data berhasil ditambah", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(TambahDataActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }
}
