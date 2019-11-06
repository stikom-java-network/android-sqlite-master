package com.example.simple_app_with_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple_app_with_sqlite.Database.DatabaseHandler;
import com.example.simple_app_with_sqlite.Model.BarangModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailBarangActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nama_barang)
    TextView txtNamaBarang;
    @BindView(R.id.kode_barang)
    TextView txtKodeBarang;
    @BindView(R.id.satuan_barang)
    TextView txtSatuanBarang;
    @BindView(R.id.harga_beli)
    TextView txtHargaBeli;
    @BindView(R.id.harga_jual)
    TextView txtHargaJual;

    private ArrayList<BarangModel> listBarang;
    String id;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);
        ButterKnife.bind(this);

        Intent i = getIntent();
        id = i.getStringExtra("id");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DatabaseHandler db = new DatabaseHandler(this);

        listBarang = db.getBarang(id);
        setData();
    }

    private void setData(){
        txtNamaBarang.setText(listBarang.get(0).getNama_barang());
        txtKodeBarang.setText(listBarang.get(0).getKode_barang());
        txtSatuanBarang.setText(listBarang.get(0).getSatuan());
        txtHargaBeli.setText(String.valueOf(listBarang.get(0).getHarga_beli()));
        txtHargaJual.setText(String.valueOf(listBarang.get(0).getHarga_jual()));
    }

    @OnClick(R.id.hapusData)
    void hapus(){
        showDialog();
    }

    private void showDialog(){
        CharSequence colors[] = new CharSequence[]{"Batal", "Hapus"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apa anda ingin menghapus?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(DetailBarangActivity.this, "Okey", Toast.LENGTH_SHORT).show();
                if (db.hapus_barang(id) == 1){
                    Toast.makeText(DetailBarangActivity.this, "Berhasil menghapus", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(DetailBarangActivity.this, MainActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(in);
                }
            }
        }).setNegativeButton("No", null).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
