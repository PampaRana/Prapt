package com.prapt.prapt.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.prapt.prapt.R;
import com.prapt.prapt.activity.BrandActivity;
import com.prapt.prapt.activity.SubCategoriesItem;
import com.prapt.prapt.model.subCategory.SubCategoryDataDetails;
import com.prapt.prapt.pogo.SubcatagoriessetGet;

import java.util.ArrayList;
import java.util.List;

public class SubcategoriesAdapter extends RecyclerView.Adapter<SubcategoriesAdapter.ViewHolder> {
        private BrandActivity mCtx;
        private ArrayList<SubCategoryDataDetails> subcatagoriessetGets;
        int listview;
        public SubcategoriesAdapter(BrandActivity mCtx,  ArrayList<SubCategoryDataDetails> subcatagoriessetGets) {
            this.mCtx = mCtx;
            this.subcatagoriessetGets = subcatagoriessetGets;
        }
        @Override
        public SubcategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_categories_list, parent, false);
            return new SubcategoriesAdapter.ViewHolder(view);

        }
        @Override
        public void onBindViewHolder(final SubcategoriesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            SubCategoryDataDetails itemsView = subcatagoriessetGets.get(position);
            listview = position;
            holder.led_tv.setText(itemsView.getLink_name());

            Glide.with(mCtx)
                    .load(itemsView.getDfile1())
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            holder.content_loading_pb.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.profile_image)
            ;

            //holder.profile_image.setImageDrawable(mCtx.getResources().getDrawable(cat.getSubImage()));
            holder.Ids.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, SubCategoriesItem.class);
                    mCtx.startActivity(intent);
                    /*if (cat.getLink_name().equals("Powder")){
                        Intent intent = new Intent(mCtx, SubCategoriesItem.class);
                        mCtx.startActivity(intent);
                    }*/
                }
            });
        }
        @Override
        public int getItemCount() {
            return subcatagoriessetGets.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView profile_image;
            private TextView led_tv;
            private RelativeLayout Ids;
            ContentLoadingProgressBar content_loading_pb;
            public ViewHolder(View view) {
                super(view);
                led_tv = (TextView) view.findViewById(R.id.led_tv);
                profile_image = (ImageView) view.findViewById(R.id.profile_image);
                Ids = (RelativeLayout) view.findViewById(R.id.Ids);
                content_loading_pb=view.findViewById(R.id.content_loading_pb);

            }
        }
    }
