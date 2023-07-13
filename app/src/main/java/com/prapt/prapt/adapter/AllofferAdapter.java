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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.prapt.prapt.model.allOffer.AllOfferDataDetails;
import com.prapt.prapt.pogo.AllOfferList;
import com.prapt.prapt.R;

import java.util.ArrayList;
import java.util.List;

public class AllofferAdapter extends RecyclerView.Adapter<AllofferAdapter.ViewHolder> {
        private Context mCtx;
        private ArrayList<AllOfferDataDetails> allOfferListList;
        int listview;
        public AllofferAdapter(Context mCtx,  ArrayList<AllOfferDataDetails> allOfferListList) {
            this.mCtx = mCtx;
            this.allOfferListList = allOfferListList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_offers_list, parent, false);
            return new ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            AllOfferDataDetails cat = allOfferListList.get(position);
            listview = position;

            Glide.with(mCtx)
                    .load(cat.getDfile1())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                           // holder.content_loading_pb.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.image1)
            ;
            holder.titleName.setText(cat.getP_title());
            if (!cat.getExpires().equalsIgnoreCase("")) {
                if (Integer.parseInt(cat.getExpires()) > 1) {
                    holder.offerExchange.setText("Offer Expires in " + cat.getExpires() + " days");
                } else {
                    holder.offerExchange.setText("Offer Expires in " + cat.getExpires() + " day");

                }
            }
            for (int i=0;i<cat.getAllOfferInfoDetails().size();i++){

                holder.offerText1.setText(
                        String.join(" ", cat.getAllOfferInfoDetails().get(i).getDetails()) );

            }

            /*holder.image1.setImageDrawable(mCtx.getResources().getDrawable(cat.getImage1()));
            holder.image2.setImageDrawable(mCtx.getResources().getDrawable(cat.getImage2()));
            holder.image3.setImageDrawable(mCtx.getResources().getDrawable(cat.getImage3()));
            holder.offerExchange.setText(String.valueOf(cat.getOfferExchange()));
            holder.offerText1.setText(String.valueOf(cat.getOfferText1()));
            holder.offerText2.setText(String.valueOf(cat.getOfferText2()));
            holder.offerText3.setText(String.valueOf(cat.getOfferText3()));
            holder.offerText4.setText(String.valueOf(cat.getOfferText4()));
            holder.offerText5.setText(String.valueOf(cat.getOfferText5()));
            holder.offerText11.setText(String.valueOf(cat.getOfferText11()));*/

        }
        @Override
        public int getItemCount() {
            return allOfferListList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView image1,image2,image3,image4,image5;
            private TextView titleName,offerExchange,offerText1,offerText2,offerText3,offerText4,offerText5,offerText11;
            public ViewHolder(View view) {
                super(view);
                titleName = (TextView) view.findViewById(R.id.titleName);
                offerExchange = (TextView) view.findViewById(R.id.offerExchange);
                offerText1 = (TextView) view.findViewById(R.id.offerText1);
                offerText2 = (TextView) view.findViewById(R.id.offerText2);
                offerText3 = (TextView) view.findViewById(R.id.offerText3);
                offerText4 = (TextView) view.findViewById(R.id.offerText4);
                offerText5 = (TextView) view.findViewById(R.id.offerText5);
                offerText11 = (TextView) view.findViewById(R.id.offerText11);
                image1 = (ImageView) view.findViewById(R.id.image1);
                image2 = (ImageView) view.findViewById(R.id.image2);
                image3 = (ImageView) view.findViewById(R.id.image3);
                image4 = (ImageView) view.findViewById(R.id.image4);
                image5 = (ImageView) view.findViewById(R.id.image5);
            }
        }
    }