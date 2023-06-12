package com.prapt.prapt.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prapt.prapt.R;
import com.prapt.prapt.pogo.DamageSetGetview;

import java.util.List;
public class DamageViewAdapter extends RecyclerView.Adapter<DamageViewAdapter .ViewHolder> {
        private Context mCtx;
        private List<DamageSetGetview> damageSetGetviewList;
        int listview;
        public DamageViewAdapter(Context mCtx,  List<DamageSetGetview> damageSetGetviewList) {
            this.mCtx = mCtx;
            this.damageSetGetviewList = damageSetGetviewList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.damage_adapter_view, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            DamageSetGetview damageSetGetview = damageSetGetviewList.get(position);
            listview = position;
            holder.typeData.setText(String.valueOf(damageSetGetview.getDamageType()));
            holder.raiseddataId.setText(String.valueOf(damageSetGetview.getDamageRaisedQty()));
            holder.approverDataId.setText(String.valueOf(damageSetGetview.getDamageApprovedQty()));
            holder.nameId.setText(String.valueOf(damageSetGetview.getDamageName()));
            Glide.with(mCtx)
                    .load(damageSetGetview.getImage())
                    .into(holder.damge_Image);
        }
        @Override
        public int getItemCount() {
            return damageSetGetviewList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView typeData,raiseddataId,approverDataId,nameId;
            private ImageView damge_Image;
            LinearLayout lv;

            public ViewHolder(View view) {
                super(view);
                typeData = (TextView) view.findViewById(R.id.typeData);
                raiseddataId = (TextView) view.findViewById(R.id.raiseddataId);
                approverDataId = (TextView) view.findViewById(R.id.approverDataId);
                damge_Image = (ImageView) view.findViewById(R.id.damge_Image);
                nameId = (TextView) view.findViewById(R.id.nameId);
            }
        }
    }
