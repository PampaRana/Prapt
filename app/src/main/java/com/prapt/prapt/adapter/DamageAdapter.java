package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.pogo.DamageSetGet;
import com.prapt.prapt.R;

import java.util.List;

public class DamageAdapter extends RecyclerView.Adapter<DamageAdapter.ViewHolder> {
    private Context mCtx;
    private List<DamageSetGet> damageSetGetList;
    int listview;
    public DamageAdapter(Context mCtx, List<DamageSetGet> damageSetGetList) {
        this.mCtx = mCtx;
        this.damageSetGetList = damageSetGetList;
    }
    @Override
    public DamageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.damage_adapter, parent, false);
        return new DamageAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final DamageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DamageSetGet damageSetGet = damageSetGetList.get(position);
        listview = position;
        holder.nameId.setText(String.valueOf(damageSetGet.getDamageName()));
        holder.dateId.setText(String.valueOf(damageSetGet.getDamageDate()));
        holder.itemId.setText(String.valueOf(damageSetGet.getDamageItem()));
        holder.amountId.setText(String.valueOf(damageSetGet.getDamageRequestAmount()));
        holder.statuesId.setText(String.valueOf(damageSetGet.getStatues()));


    }
    @Override
    public int getItemCount() {
        return damageSetGetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private TextView nameId,dateId,itemId,amountId,statuesId;

        public ViewHolder(View view) {
            super(view);
            nameId = (TextView) view.findViewById(R.id.nameId);
            dateId = (TextView) view.findViewById(R.id.dateId);
            itemId = (TextView) view.findViewById(R.id.itemId);
            amountId = (TextView) view.findViewById(R.id.amountId);
            statuesId = (TextView) view.findViewById(R.id.statuesId);

        }
    }
}


