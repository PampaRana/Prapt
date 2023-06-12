package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.prapt.prapt.R;
import com.prapt.prapt.pogo.SubViewSecondSetGet;

import java.util.List;
    public class SubcatagoriSecondAdapter extends RecyclerView.Adapter<SubcatagoriSecondAdapter.ViewHolder> {
        private Context mCtx;
        private List<SubViewSecondSetGet> subViewSecondSetGetList;
        int listview;
        public SubcatagoriSecondAdapter(Context mCtx, List<SubViewSecondSetGet> subViewSecondSetGetList) {
            this.mCtx = mCtx;
            this.subViewSecondSetGetList = subViewSecondSetGetList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_catagories_item_views, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            SubViewSecondSetGet homeitemViewCatagorySetGet = subViewSecondSetGetList.get(position);
            listview = position;
            holder.text_kg.setText(String.valueOf(homeitemViewCatagorySetGet.getSubKg()));
            if (homeitemViewCatagorySetGet.getSubKg().equals("10.Rs/-")){
                holder.persentImage.setVisibility(View.GONE);
                holder.text_kg.setBackgroundDrawable(mCtx.getResources().getDrawable(R.drawable.text_rounds));
            }
            else if (homeitemViewCatagorySetGet.getSubKg().equals("1.5Kg")){
                 holder.persentImage.setVisibility(View.GONE);
            }
            else if (homeitemViewCatagorySetGet.getSubKg().equals("5Kg")){
                holder.persentImage.setVisibility(View.GONE);

            }
            else if (homeitemViewCatagorySetGet.getSubKg().equals("4Kg")){
                holder.persentImage.setVisibility(View.GONE);

            }
            else {
                holder.persentImage.setVisibility(View.VISIBLE);

            }
        }
        @Override
        public int getItemCount() {
            return subViewSecondSetGetList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView text_kg;
            ImageView persentImage;
            public ViewHolder(View view) {
                super(view);
                text_kg = (TextView) view.findViewById(R.id.text_kg);
                persentImage = (ImageView) view.findViewById(R.id.persentImage);

            }
        }
    }
