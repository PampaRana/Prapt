package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prapt.prapt.R;
import com.prapt.prapt.pogo.WishListSetGet;

import java.util.List;

public class WishlistViewAdapter extends RecyclerView.Adapter<WishlistViewAdapter.ViewHolder> {
    private Context mCtx;
    private List<WishListSetGet> wishListSetGetList;
    int listview;
    public WishlistViewAdapter(Context mCtx, List<WishListSetGet> wishListSetGetList) {
        this.mCtx = mCtx;
        this.wishListSetGetList = wishListSetGetList;
    }
    @Override
    public WishlistViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list_adapter, parent, false);
        return new WishlistViewAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final WishlistViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        WishListSetGet wishListSetGet = wishListSetGetList.get(position);
        listview = position;
        holder.wishName.setText(String.valueOf(wishListSetGet.getWishName()));
        holder.wishGram.setText(String.valueOf(wishListSetGet.getWishGram()));
        holder.wishPrice.setText(String.valueOf(wishListSetGet.getWishPrice()));
        holder.discountText.setText(String.valueOf(wishListSetGet.getWishOfferPrice()));
////            //holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(homeitemViewCatagorySetGet.getImg()));
        Glide.with(mCtx)
                .load(wishListSetGet.getWishImage())
                .into(holder.wish_Image);
    }
    @Override
    public int getItemCount() {
        return wishListSetGetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private TextView wishName,wishGram,wishPrice,discountText;
       private ImageView wish_Image,deleteId;
        public ViewHolder(View view) {
            super(view);
            wishName = (TextView) view.findViewById(R.id.wishName);
            wishGram = (TextView) view.findViewById(R.id.wishGram);
            wishPrice = (TextView) view.findViewById(R.id.wishPrice);
            discountText = (TextView) view.findViewById(R.id.discountText);
            wish_Image = (ImageView) view.findViewById(R.id.wish_Image);
            deleteId = (ImageView) view.findViewById(R.id.deleteId);
        }
    }
}

