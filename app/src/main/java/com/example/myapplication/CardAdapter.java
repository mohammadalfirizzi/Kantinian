package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Util.SessionManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.Holder> {
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//    private SharedPreference sp;
    private SessionManager sm;
    private List<DataModel> dataModels;
    private static int lastClickedPosition = -1;
    private int selectedItem;
    private Context ctx;

    public CardAdapter (Context ctx, List<DataModel> dataModels){
        this.ctx = ctx;
        this.dataModels = dataModels;
        selectedItem = 0;
    }
    @Override
    public CardAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_viewdua, parent, false);
        Holder holder = new Holder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CardAdapter.Holder holder, final int position) {
        DataModel dm = dataModels.get(position);
        holder.nama.setText(dm.getNama_customer());
        holder.nisn.setText(dm.getId_customer());
        holder.valid.setText(dm.getMati_kartu());
        holder.saldo.setText(formatRupiah.format(Integer.parseInt(dm.getSaldo_kartu())));
        HashMap<String, String> map = sm.getDetailLogin();
//        holder.valid.setText(map.get(sm.KEY_POSISI));
        selectedItem = Integer.parseInt(map.get(sm.KEY_POSISI));
//        Toast.makeText(ctx,""+selectedItem, Toast.LENGTH_LONG).show();
//        holder.cardView.setCardBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
//        if (selectedItem == position) {
//                holder.cardView.setCardBackgroundColor(ctx.getResources().getColor(R.color.merahhh));
//        }
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int previousItem = selectedItem;
//                selectedItem = position;
//
//                notifyItemChanged(previousItem);
//                notifyItemChanged(position);
////                Toast.makeText(v.getContext(),""+position,Toast.LENGTH_SHORT).show();
//                Toast.makeText(v.getContext(),""+holder.nisn.getText().toString(),Toast.LENGTH_SHORT).show();
//                sm.simpan(holder.nisn.getText().toString());
//                sm.widodo(position);
//            }
//        });
        holder.tekan.setBackgroundResource(R.drawable.custom_button_login2);
        holder.tekan.setText("Jadikan Rekening Utama");
        holder.tekan.setTextColor(0x7fff0000);

//        holder.tekan.setTextColor(getApplication().getResources().getColor(R.color.red))
//        holder.tekan.setTextColor(R.color.putih);
        if (selectedItem == position){
            holder.tekan.setBackgroundResource(R.drawable.custom_button_edit);
            holder.tekan.setText("Rekening Utama");
//            holder.tekan.setTextColor(Color.parseColor("#fff"));
            holder.tekan.setTextColor(0xFFFFFF00);
        }
        holder.tekan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousItem = selectedItem;
                selectedItem = position;

                notifyItemChanged(previousItem);
                notifyItemChanged(position);
//                Toast.makeText(v.getContext(),""+position,Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(),""+holder.nisn.getText().toString(),Toast.LENGTH_SHORT).show();
                sm.simpan(holder.nisn.getText().toString());
                sm.widodo(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
//        return (dataModels != null) ? dataModels.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView nama,nisn,valid,saldo;
        private CardView cardView;
        Button tekan;
        public Holder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.namaanak);
            nisn = (TextView) itemView.findViewById(R.id.nisnanak);
            valid = (TextView) itemView.findViewById(R.id.validanak);
            saldo = (TextView) itemView.findViewById(R.id.saldodalem);
            cardView = (CardView) itemView.findViewById(R.id.carddalem);
            tekan = (Button) itemView.findViewById(R.id.buttondalem);
            sm = new SessionManager(itemView.getContext());
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    notifyItemChanged(selectedItem);
//                    selectedItem = getLayoutPosition();
//                    notifyItemChanged(selectedItem);
//                    Toast.makeText(v.getContext(),""+nisn.getText().toString(),Toast.LENGTH_SHORT).show();
//                    sm.simpan(nisn.getText().toString());
////                    Intent intent = new Intent(v.getContext(), Hasil.class);
////                    v.getContext().startActivity(intent);
//                }
//            });
        }


    }
}
