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
import com.prapt.prapt.pogo.ProductDetailsList;

import java.util.List;

public class ProductDetailsListAdapter extends RecyclerView.Adapter<ProductDetailsListAdapter.ViewHolder> {
        private Context mCtx;
        private List<ProductDetailsList> productDetailsListList;
        int listview;

        public ProductDetailsListAdapter(Context mCtx, List<ProductDetailsList> productDetailsListList) {
            this.mCtx = mCtx;
            this.productDetailsListList = productDetailsListList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_list, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            ProductDetailsList cat = productDetailsListList.get(position);
            listview = position;
            holder.subText.setText(cat.getTitle());
            holder.subimageTag.setImageDrawable(mCtx.getResources().getDrawable(cat.getImage()));
        }
        @Override
        public int getItemCount() {
            return productDetailsListList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView subimageTag;
            TextView subText,subapply;
            public ViewHolder(View view) {
                super(view);
                subText = (TextView) view.findViewById(R.id.subText);
                subimageTag = (ImageView) view.findViewById(R.id.subimageTag);
                subapply = (TextView) view.findViewById(R.id.subapply);
            }
        }
    }