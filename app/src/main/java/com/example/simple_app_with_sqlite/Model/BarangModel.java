package com.example.simple_app_with_sqlite.Model;

public class BarangModel {
    private String kode_barang;
    private String nama_barang;
    private String satuan;
    private String kategori;
    private int harga_beli;
    private int harga_jual;

    public BarangModel(String kode_barang, String nama_barang, String satuan, String kategori, int harga_beli, int harga_jual) {
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.satuan = satuan;
        this.kategori = kategori;
        this.harga_beli = harga_beli;
        this.harga_jual = harga_jual;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getKategori() {
        return kategori;
    }

    public int getHarga_beli() {
        return harga_beli;
    }

    public int getHarga_jual() {
        return harga_jual;
    }
}
