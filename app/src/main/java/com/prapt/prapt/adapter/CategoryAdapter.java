package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.prapt.prapt.model.category.CategoryDataDetails;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context mCtx;
    private ArrayList<CategoryDataDetails> itemViews;
    int listview;
    public CategoryAdapter(Context mCtx, ArrayList<CategoryDataDetails> itemViews) {
        this.mCtx = mCtx;
        this.itemViews = itemViews;
    }
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryDataDetails itemsView = itemViews.get(position);
        listview = position;
            holder.led_tv.setText(String.valueOf(itemsView.getPropage_name()));
//            //holder.imageItem.setImageDrawable(mCtx.getResources().getDrawable(homeitemViewCatagorySetGet.getImg()));

        Glide.with(mCtx)
                .load(itemsView.getBanner_image())
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
           /* Glide.with(mCtx)
                    .load(itemsView.getBanner_image())
                    .into(holder.profile_image);*/
            holder.lv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String brand = itemsView.getPropage_name();
                    Intent intent = new Intent(mCtx, BrandActivity.class);
                    intent.putExtra("cat_id",itemsView.getPropage_id());
                    intent.putExtra("brand_name",itemsView.getPropage_name());
                    mCtx.startActivity(intent);
                  /*  if (brand.equals("Detergents")){
                        Intent intent = new Intent(mCtx, BrandActivity.class);
                        mCtx.startActivity(intent);
                    }*/
                }
            });

    }
    @Override
    public int getItemCount() {
        return itemViews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView led_tv;
            private CircleImageView profile_image;
            RelativeLayout lv;
            ContentLoadingProgressBar content_loading_pb;
        public ViewHolder(View view) {
            super(view);
                led_tv = (TextView) view.findViewById(R.id.led_tv);
            profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
            lv =  view.findViewById(R.id.lv);
            content_loading_pb=view.findViewById(R.id.content_loading_pb);
        }
    }
}

