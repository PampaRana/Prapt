package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.R;
import com.prapt.prapt.pogo.Toppacks;

import java.util.List;

public class TopPacksAdapter extends RecyclerView.Adapter<TopPacksAdapter.ViewHolder> {
    private Context mCtx;
    private List<Toppacks> toppacks;
    int listview;

    public TopPacksAdapter(Context mCtx, List<Toppacks> toppacks) {
        this.mCtx = mCtx;
        this.toppacks = toppacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_packs, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Toppacks cat = toppacks.get(position);
        listview = position;
        holder.image.setImageDrawable(mCtx.getResources().getDrawable(cat.getItemImage()));
    }
    @Override
    public int getItemCount() {
        return toppacks.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}
