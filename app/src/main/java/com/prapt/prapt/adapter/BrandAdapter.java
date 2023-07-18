package com.prapt.prapt.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.prapt.prapt.activity.BrandActivity;
import com.prapt.prapt.model.brand.BrandDataDetails;
import com.prapt.prapt.pogo.BrandSetGet;
import com.prapt.prapt.R;

import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    private BrandActivity mCtx;
    private ArrayList<BrandDataDetails> brandSetGets;
    int listview;
    detailsListener detailsListener;

    public BrandAdapter(BrandActivity mCtx,  ArrayList<BrandDataDetails> brandSetGets,
                        detailsListener detailsListener) {
        this.mCtx = mCtx;
        this.brandSetGets = brandSetGets;
        this.detailsListener = detailsListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_set_get, parent, false);
        return new ViewHolder(view,detailsListener);

    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BrandDataDetails itemsView = brandSetGets.get(position);
        listview = position;
        holder.led_tv.setText(itemsView.getBrand_name());
        Glide.with(mCtx)
                .load(itemsView.getBrand_logo())
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

        holder.Ids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsListener.goToDetailsBrand(brandSetGets.get(position).getBrand_id());
            }
        });


       // holder.profile_image.setImageDrawable(mCtx.getResources().getDrawable(cat.getBrand_logo()));
    }
    @Override
    public int getItemCount() {
        return brandSetGets.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profile_image;
        private TextView led_tv;
        private RelativeLayout Ids;
        ContentLoadingProgressBar content_loading_pb;
        detailsListener detailsListener;
        public ViewHolder(View view,detailsListener detailsListener) {
            super(view);
            this.detailsListener=detailsListener;
             led_tv = (TextView) view.findViewById(R.id.led_tv);
             profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
             Ids = (RelativeLayout) view.findViewById(R.id.Ids);
            content_loading_pb=view.findViewById(R.id.content_loading_pb);

        }
    }
    public interface detailsListener{
        void goToDetailsBrand(String brand_id);
    }
}