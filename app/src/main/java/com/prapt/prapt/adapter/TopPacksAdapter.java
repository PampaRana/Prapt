package com.prapt.prapt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.prapt.prapt.R;
import com.prapt.prapt.model.topOffer.TopOfferDataDetails;
import com.prapt.prapt.pogo.Toppacks;

import java.util.ArrayList;
import java.util.List;

public class TopPacksAdapter extends RecyclerView.Adapter<TopPacksAdapter.ViewHolder> {
    private Context mCtx;
    private ArrayList<TopOfferDataDetails> toppacks;
    int listview;

    public TopPacksAdapter(Context mCtx, ArrayList<TopOfferDataDetails> toppacks) {
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
        TopOfferDataDetails cat = toppacks.get(position);
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
                        //holder.content_loading_pb.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.image)
        ;
        //holder.image.setImageDrawable(mCtx.getResources().getDrawable(cat.getDfile1()));
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
