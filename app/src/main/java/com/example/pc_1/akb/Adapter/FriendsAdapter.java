package com.example.pc_1.akb.Adapter;
/**
 21/05/2019
 10116125
 Al Ghani Iqbal Dzulfiqar
 AKB -3
 **/
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc_1.akb.DetailActivity;
import com.example.pc_1.akb.Model.FriendModel;
import com.example.pc_1.akb.Model.Friends;
import com.example.pc_1.akb.R;

import java.util.ArrayList;
import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {
    private List<FriendModel> friendModels;
    Context context;

    public FriendsAdapter(Context context, List<FriendModel> friendModels){
        this.context = context;
        this.friendModels = friendModels;
    }

    @Override
    public FriendsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mahasiswa, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(FriendsAdapter.MyViewHolder holder, int position) {
        final FriendModel model = friendModels.get(position);
        int snim = model.getNim();
        holder.nim.setText(Integer.toString(snim));
        holder.nama.setText(model.getNama());
        holder.kelas.setText(model.getKelas());
        holder.telp.setText(model.getTelp());
        holder.email.setText(model.getEmail());
        holder.socmed.setText(model.getSocmed());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                int snim = model.getNim();
                int sid = model.getId();
                intent.putExtra("id", Integer.toString(sid));
                intent.putExtra("nim", Integer.toString(snim));
                intent.putExtra("nama", model.getNama());
                intent.putExtra("kelas",model.getKelas());
                intent.putExtra("telp",model.getTelp());
                intent.putExtra("email",model.getEmail());
                intent.putExtra("socmed",model.getSocmed());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nim, nama,kelas,telp,email,socmed;

        public MyViewHolder(View itemView){
            super(itemView);
            nim = itemView.findViewById(R.id.txt_nim);
            nama = itemView.findViewById(R.id.txt_nama);
            kelas = itemView.findViewById(R.id.txt_kelas);
            telp = itemView.findViewById(R.id.txt_telp);
            email = itemView.findViewById(R.id.txt_email);
            socmed = itemView.findViewById(R.id.txt_socmed);
        }
    }
}