package com.prapt.prapt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.prapt.prapt.R;
import com.prapt.prapt.model.topOffer.TopOfferDataDetails;
import com.prapt.prapt.pogo.Categories;

import java.util.List;

public class TopOfferAdapter extends RecyclerView.Adapter<TopOfferAdapter.ViewHolder> {
    private Context mCtx;
    private List<TopOfferDataDetails> topOfferList;

    public TopOfferAdapter(Context mCtx, List<TopOfferDataDetails> topOfferList) {
        this.mCtx = mCtx;
        this.topOfferList = topOfferList;
    }

    @NonNull
    @Override
    public TopOfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new TopOfferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopOfferAdapter.ViewHolder holder, int position) {

        Glide.with(mCtx)
                .load(topOfferList.get(position).getDfile1())
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
                .into(holder.courseIV)
        ;
    }

    @Override
    public int getItemCount() {
        return topOfferList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ContentLoadingProgressBar content_loading_pb;
        ImageView courseIV;
        TextView courseTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content_loading_pb=itemView.findViewById(R.id.content_loading_pb);
            courseTV=itemView.findViewById(R.id.idTVCourse);
            courseIV=itemView.findViewById(R.id.idIVcourse);

        }
    }
}
