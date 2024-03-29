package com.example.simple_app_with_sqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.simple_app_with_sqlite.Model.BarangModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_kasir";
    private static final String TABLE_NAME = "tb_barang";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+
                "kode_barang  varchar(20) primary key, "+
                "nama_barang varhcar(50)," +
                "satuan varchar(50)," +
                "kategori varchar(50), "+
                "harga_beli int(20), "+
                "harga_jual int(20));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int addRecord(BarangModel barangModels){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kode_barang", barangModels.getKode_barang());
        values.put("nama_barang", barangModels.getNama_barang());
        values.put("satuan", barangModels.getSatuan());
        values.put("kategori", barangModels.getKategori());
        values.put("harga_beli", barangModels.getHarga_beli());
        values.put("harga_jual", barangModels.getHarga_jual());
        db.insert(TABLE_NAME, null, values);
        db.close();

        return 1;
    }

    public ArrayList<BarangModel> getBarang(String id){
        ArrayList<BarangModel> listBarang = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE kode_barang = '"+id+"'", null);
        int i = 0;
        if (cur.getCount() > 0) cur.moveToFirst();
        while (i < cur.getCount()){
            BarangModel barangModel = new BarangModel(
                    cur.getString(cur.getColumnIndex("kode_barang")),
                    cur.getString(cur.getColumnIndex("nama_barang")),
                    cur.getString(cur.getColumnIndex("satuan")),
                    cur.getString(cur.getColumnIndex("kategori")),
                    cur.getInt(cur.getColumnIndex("harga_beli")),
                    cur.getInt(cur.getColumnIndex("harga_jual"))
            );
            listBarang.add(barangModel);
            cur.moveToNext();
            i++;
        }
        db.close();
        return listBarang;
    }

    public List<BarangModel> getAllRecord(){
        List<BarangModel> userList = new ArrayList<>();
        String getAllQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getAllQuery, null);
        if(cursor.moveToFirst()){
            do {
                BarangModel barangModel = new BarangModel(
                        cursor.getString(cursor.getColumnIndex("kode_barang")),
                        cursor.getString(cursor.getColumnIndex("nama_barang")),
                        cursor.getString(cursor.getColumnIndex("satuan")),
                        cursor.getString(cursor.getColumnIndex("kategori")),
                        cursor.getInt(cursor.getColumnIndex("harga_beli")),
                        cursor.getInt(cursor.getColumnIndex("harga_jual"))
                );
                userList.add(barangModel);
            }while (cursor.moveToNext());
        }
        db.close();
        return userList;
    }

    public int hapus_barang(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "kode_barang = '"+id+"'", null);
        db.close();

        return 1;
    }
}
