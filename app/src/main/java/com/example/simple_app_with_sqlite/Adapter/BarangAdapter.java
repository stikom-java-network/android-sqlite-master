package com.example.simple_app_with_sqlite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simple_app_with_sqlite.DetailBarangActivity;
import com.example.simple_app_with_sqlite.Model.BarangModel;
import com.example.simple_app_with_sqlite.R;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {

    private List<BarangModel> barangList;
    private Context context;

    public BarangAdapter(Context context, List<BarangModel> barangList) {
        this.barangList = barangList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BarangModel barangModel = barangList.get(position);

        holder.txtNamaBarang.setText(barangModel.getNama_barang());
        holder.txtKodeBarang.setText(barangModel.getKode_barang());
        holder.txtSatuanBarang.setText(barangModel.getSatuan());
        holder.txtHargaBarang.setText(barangModel.getHarga_beli()+" - "+barangModel.getHarga_jual());
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.nama_barang)
        TextView txtNamaBarang;
        @BindView(R.id.kode_barang)
        TextView txtKodeBarang;
        @BindView(R.id.satuan_barang)
        TextView txtSatuanBarang;
        @BindView(R.id.harga_barang)
        TextView txtHargaBarang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String id = txtKodeBarang.getText().toString();
//            Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), DetailBarangActivity.class);
            i.putExtra("id", id);
            view.getContext().startActivity(i);
        }
    }
}
